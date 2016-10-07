package edu.hawaii.its.holiday.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.hawaii.its.holiday.access.AnonymousUser;
import edu.hawaii.its.holiday.access.Role;
import edu.hawaii.its.holiday.access.User;
import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;
import edu.hawaii.its.holiday.security.UserContextService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DatabaseConfig.class, WebConfig.class, CachingConfig.class })
public class UserContextServiceSystemTest {

    @Autowired
    private UserContextService userContextService;

    @Test
    public void user() {
        assertNotNull(userContextService);
        User user = userContextService.getCurrentUser();
        assertTrue(user instanceof AnonymousUser);
        assertTrue(user.isEnabled());
        assertEquals("anonymous", user.getUsername());
        assertEquals("anonymous", user.getUid());
        assertEquals(1, user.getAuthorities().size());
        assertTrue(user.hasRole(Role.ANONYMOUS));
        assertNotNull(user.getAttributes());
        assertEquals("", user.getAttribute("uhuuid"));
    }
}
