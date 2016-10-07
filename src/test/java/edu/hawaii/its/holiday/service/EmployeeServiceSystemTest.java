package edu.hawaii.its.holiday.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;
import edu.hawaii.its.holiday.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DatabaseConfig.class, WebConfig.class, CachingConfig.class })
@DirtiesContext
public class EmployeeServiceSystemTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void exists() {
        assertTrue(employeeService.exists("89999999"));
        assertTrue(employeeService.exists("10000004"));

        assertFalse(employeeService.exists(null));
        assertFalse(employeeService.exists(""));
        assertFalse(employeeService.exists("  "));
        assertFalse(employeeService.exists("no-way-none"));
    }
}
