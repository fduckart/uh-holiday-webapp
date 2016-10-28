package edu.hawaii.its.holiday.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
public class HolidayRestControllerSystemTest {

    @Autowired
    HolidayRestController controller;

    @Test
    public void test() {
        assertNotNull(controller.holiday(1));
    }
}
