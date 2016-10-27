package edu.hawaii.its.holiday.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
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
import edu.hawaii.its.holiday.type.Type;
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

        Holiday h0 = holidays.get(0);
        assertNotNull(h0);
        Holiday h1 = holidayService.findHoliday(h0.getId());
        assertEquals(h0.getId(), h1.getId());
        assertEquals(h0, h1);
    }

    @Test
    public void findHolidaysByYear() {
        List<Holiday> holidays = holidayService.findHolidays(2016);
        assertThat(holidays.size(), equalTo(14));

        holidays = holidayService.findHolidays(2014);
        assertThat(holidays.size(), equalTo(15));

        holidays = holidayService.findHolidays(2011);
        assertThat(holidays.size(), equalTo(14));
        System.out.println(Strings.fill('v', 99));
        System.out.println(">>> year: 2011 ...");
        holidays.forEach(h -> System.out.println(h));
        System.out.println(Strings.fill('^', 99));

        holidays = holidayService.findHolidays(2010);
        assertThat(holidays.size(), equalTo(1));
        System.out.println(Strings.fill('v', 99));
        System.out.println(">>> year: 2010 ...");
        holidays.forEach(h -> System.out.println(h));
        System.out.println(Strings.fill('^', 99));
    }

    @Test
    public void findTypes() {
        List<Type> types = holidayService.findTypes();

        Type ht = types.get(0);
        assertThat(ht.getId(), equalTo(1));
        assertThat(ht.getVersion(), equalTo(1));
        assertThat(ht.getDescription(), equalTo("Bank"));

        ht = types.get(1);
        assertThat(ht.getId(), equalTo(2));
        assertThat(ht.getVersion(), equalTo(1));
        assertThat(ht.getDescription(), equalTo("Federal"));

        ht = types.get(2);
        assertThat(ht.getId(), equalTo(3));
        assertThat(ht.getVersion(), equalTo(1));
        assertThat(ht.getDescription(), equalTo("State"));

        ht = types.get(3);
        assertThat(ht.getId(), equalTo(4));
        assertThat(ht.getVersion(), equalTo(1));
        assertThat(ht.getDescription(), equalTo("UH"));
    }

    @Test
    public void findHolidayTypes() {
        List<HolidayType> mappings = holidayService.findHolidayTypes();
        assertTrue(mappings.size() > 50);
        HolidayType ht = mappings.get(0);
        assertThat(ht.getTypeId(), equalTo(1));
        assertThat(ht.getHolidayId(), equalTo(1));

        Holiday h = holidayService.findHoliday(ht.getHolidayId());
        assertThat(h.getId(), equalTo(1));
        assertThat(h.getVersion(), equalTo(0));
        assertThat(h.getDescription(), equalTo("New Year's Day"));

        Type t = holidayService.findType(ht.getTypeId());
        assertThat(t.getId(), equalTo(1));
        assertThat(t.getVersion(), equalTo(1));
        assertThat(t.getDescription(), equalTo("Bank"));

        // Make sure caching is working.
        Integer id = ht.getId();
        HolidayType ht0 = holidayService.findHolidayType(id);
        HolidayType ht1 = holidayService.findHolidayType(id);
        assertSame(ht0, ht1);
        assertEquals(ht0, ht);
        assertEquals(ht1, ht);
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
