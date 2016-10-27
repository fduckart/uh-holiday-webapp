package edu.hawaii.its.holiday.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;
import edu.hawaii.its.holiday.type.Action;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DatabaseConfig.class, WebConfig.class, CachingConfig.class })

public class ActionLoggerSystemTest {

    @Autowired
    private ActionLogger actionLogger;

    @Test
    public void findActions() {
        List<Action> actions = actionLogger.findActions();
        assertThat(actions.size(), equalTo(12));

        Action a0 = actions.get(0);
        assertThat(a0.getId(), equalTo(10L));
        assertThat(a0.getEnabled(), equalTo("N"));
        assertThat(a0.getCode(), equalTo("employee.view.home"));
        assertNotNull(a0.getDescription());
        a0 = null;

        Action a1 = actions.get(1);
        assertThat(a1.getId(), equalTo(11L));
        assertThat(a1.getEnabled(), equalTo("Y"));
        assertThat(a1.getCode(), equalTo("employee.view.holiday"));
        assertNotNull(a1.getDescription());
        a1 = null;

        Action a9 = actions.get(actions.size() - 1);
        assertThat(a9.getId(), equalTo(33L));
        assertThat(a9.getEnabled(), equalTo("N"));
        assertThat(a9.getCode(), equalTo("admin.save.holiday"));
        assertNotNull(a9.getDescription());
    }
}
