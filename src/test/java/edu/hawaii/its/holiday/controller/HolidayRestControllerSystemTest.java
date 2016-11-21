package edu.hawaii.its.holiday.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hawaii.its.holiday.configuration.CachingConfig;
import edu.hawaii.its.holiday.configuration.DatabaseConfig;
import edu.hawaii.its.holiday.configuration.SecurityConfig;
import edu.hawaii.its.holiday.configuration.WebConfig;
import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.util.Dates;

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
    public void holidayById() {
        ResponseEntity<JsonData<Holiday>> entity = controller.holiday(1);
        assertNotNull(entity);
        assertTrue(entity.hasBody());
        assertEquals(200, entity.getStatusCodeValue());

        Holiday h0 = entity.getBody().getData();
        assertEquals("New Year's Day", h0.getDescription());
        assertEquals(Dates.newLocalDate(2013, Month.JANUARY, 1), Dates.toLocalDate(h0.getOfficialDate()));
        assertEquals(Dates.newLocalDate(2013, Month.JANUARY, 1), Dates.toLocalDate(h0.getObservedDate()));
        assertEquals(Dates.firstDateOfYear(2013), Dates.toLocalDate(h0.getOfficialDate()));
        assertEquals(Dates.firstDateOfYear(2013), Dates.toLocalDate(h0.getObservedDate()));

        entity = controller.holiday(1);
        Holiday h1 = entity.getBody().getData();
        assertEquals(h0, h1);
        assertSame(h0, h1);
    }

    @Test
    public void holidays() {
        ResponseEntity<JsonData<List<Holiday>>> entity = controller.holidays();
        ResponseEntity<JsonData<List<Holiday>>> entity0 = controller.holidays();
        assertEquals(entity, entity0);
        assertNotSame(entity, entity0); // Hmmm? 

        List<Holiday> holidays = entity.getBody().getData();
        Holiday h0 = holidays.get(0);
        assertEquals("Christmas", h0.getDescription());
        assertEquals(Dates.newLocalDate(2017, Month.DECEMBER, 25), Dates.toLocalDate(h0.getOfficialDate()));
        assertEquals(Dates.newLocalDate(2017, Month.DECEMBER, 25), Dates.toLocalDate(h0.getObservedDate()));
        h0 = null;

        Holiday h1 = holidays.get(holidays.size() - 1);
        assertEquals("New Year's Day", h1.getDescription());
        assertEquals(Dates.newLocalDate(2011, Month.JANUARY, 1), Dates.toLocalDate(h1.getOfficialDate()));
        assertEquals(Dates.newLocalDate(2010, Month.DECEMBER, 31), Dates.toLocalDate(h1.getObservedDate()));
    }

    @Test
    public void testPersistAndFindById() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Holiday holiday = new Holiday();
        holiday.setDescription("Burrito");
        holiday.setObservedDate(new Date());
        holiday.setOfficialDate(new Date());

        List<Holiday> list = new ArrayList<Holiday>();
        list.add(holiday);
        String rootName = Holiday.class.getAnnotation(JsonRootName.class).value();
        System.out.println(mapper.writer().withRootName(rootName).writeValueAsString(list));

    }
}
