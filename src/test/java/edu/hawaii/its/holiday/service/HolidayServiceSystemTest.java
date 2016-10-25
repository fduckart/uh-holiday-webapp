package edu.hawaii.its.holiday.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;
import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.type.HolidayType;
import edu.hawaii.its.holiday.type.HolidayTypeMapping;
import edu.hawaii.its.holiday.type.UserRole;
import edu.hawaii.its.holiday.util.Strings;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { DatabaseConfig.class, WebConfig.class, CachingConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class HolidayServiceSystemTest {

    @Autowired
    private HolidayService holidayService;

    @Test
    public void findHolidays() {
        List<Holiday> holidays = holidayService.findHolidays();

        System.out.println(Strings.fill('v', 88));
        holidays.forEach(h -> System.out.println("\t --> " + h));
        System.out.println(Strings.fill('^', 88));

        Holiday h0 = holidays.get(0);
        assertNotNull(h0);
        Holiday h1 = holidayService.findHoliday(h0.getId());
        assertEquals(h0.getId(), h1.getId());
        assertEquals(h0, h1);
    }

    @Test
    public void findHolidayTypes() {
        List<HolidayType> types = holidayService.findHolidayTypes();

        HolidayType ht = types.get(0);
        assertThat(ht.getId(), is(equalTo(1)));
        assertThat(ht.getVersion(), is(equalTo(1)));
        assertThat(ht.getDescription(), is(equalTo("Bank")));

        ht = types.get(1);
        assertThat(ht.getId(), is(equalTo(2)));
        assertThat(ht.getVersion(), is(equalTo(1)));
        assertThat(ht.getDescription(), is(equalTo("Federal")));

        ht = types.get(2);
        assertThat(ht.getId(), is(equalTo(3)));
        assertThat(ht.getVersion(), is(equalTo(1)));
        assertThat(ht.getDescription(), is(equalTo("State")));

        ht = types.get(3);
        assertThat(ht.getId(), is(equalTo(4)));
        assertThat(ht.getVersion(), is(equalTo(1)));
        assertThat(ht.getDescription(), is(equalTo("UH")));
    }

    @Test
    public void findHolidayTypeMappings() {
        List<HolidayTypeMapping> mappings = holidayService.findHolidayTypeMappings();
        assertTrue(mappings.size() > 10);
        HolidayTypeMapping ht = mappings.get(0);
        assertThat(ht.getTypeId(), is(equalTo(1)));
        assertThat(ht.getHolidayId(), is(equalTo(15)));
    }

    @Test
    public void findUserRoles() {
        List<UserRole> userRoles = holidayService.findUserRoles();
        assertTrue(userRoles.size() >= 2);
        assertEquals(1, userRoles.get(0).getId().intValue());
        assertEquals(2, userRoles.get(1).getId().intValue());
        assertEquals("ROLE_ADMIN", userRoles.get(0).getAuthority());
        assertEquals("ROLE_USER", userRoles.get(1).getAuthority());
    }

}
