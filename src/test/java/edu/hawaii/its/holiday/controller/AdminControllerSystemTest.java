package edu.hawaii.its.holiday.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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
public class AdminControllerSystemTest {

    @Value("${cas.login.url}")
    private String casLoginUrl;

    @Autowired
    private AdminController controller;

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
        assertNotNull(controller);
    }

    @Test
    @WithMockUhUser(username = "admin", roles = { "ROLE_UH", "ROLE_ADMIN" })
    public void admin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

    @Test
    @WithMockUhUser(username = "uh", roles = { "ROLE_UH" })
    public void adminViaUh() throws Exception {
        // Not high enough role for access.
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithAnonymousUser
    public void adminViaAnonymous() throws Exception {
        // Anonymous users not allowed into admin area.
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(casLoginUrl + "**"));
    }
}
