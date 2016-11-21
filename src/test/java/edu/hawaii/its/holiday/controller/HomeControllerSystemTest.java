package edu.hawaii.its.holiday.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.SecurityConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        SecurityConfig.class,
        DatabaseConfig.class,
        WebConfig.class,
        CachingConfig.class })
public class HomeControllerSystemTest {

    @Value("${cas.login.url}")
    private String casLoginUrl;

    @Value("${cas.send.renew}")
    private boolean casSendRenew;

    @Autowired
    private HomeController homeController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testConstruction() {
        assertNotNull(homeController);
    }

    @Test
    public void requestUrlDenied() throws Exception {
        mockMvc.perform(get("/denied"))
                .andExpect(status().isOk())
                .andExpect(view().name("denied"));
    }

    @Test
    public void requestUrl404() throws Exception {
        mockMvc.perform(get("/404"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @WithAnonymousUser
    public void attributesViaAnonymous() throws Exception {
        String expectedUrl = "https://cas-test.its.hawaii.edu/cas/login"
                + "?service=http%3A%2F%2Flocalhost%3A8080%2Fholidays%2Flogin%2Fcas";
        if (casSendRenew) {
            expectedUrl += "&renew=" + casSendRenew;
        }

        // Anonymous users not allowed here.
        mockMvc.perform(get("/attributes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectedUrl));
    }

    @Test
    @WithMockUhUser(username = "uh", roles = { "ROLE_UH" })
    public void requestUrlAttributes() throws Exception {
        mockMvc.perform(get("/attributes"))
                .andExpect(status().isOk())
                .andExpect(view().name("attributes"));
    }

    @Test
    @WithMockUhUser(username = "admin", roles = { "ROLE_UH", "ROLE_ADMIN" })
    public void attributesViaAdmin() throws Exception {
        // Should be high enough role for access.
        mockMvc.perform(get("/attributes"))
                .andExpect(status().isOk())
                .andExpect(view().name("attributes"));
    }

}
