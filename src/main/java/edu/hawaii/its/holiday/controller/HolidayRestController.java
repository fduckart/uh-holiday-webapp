package edu.hawaii.its.holiday.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hawaii.its.holiday.service.HolidayService;
import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.type.HolidayType;

@RestController
public class HolidayRestController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);

    @Autowired
    private HolidayService holidayService;

    @RequestMapping(value = "/api/holidays", method = RequestMethod.GET)
    public List<Holiday> holidays() {
        logger.info("Entered REST holidays...");
        List<Holiday> holidays = holidayService.findHolidays();
        logger.info("Leaving REST holidays.");

        return holidays;
    }

    @RequestMapping(value = "/api/holidays/{id}", method = RequestMethod.GET)
    public Holiday holiday(@PathVariable Integer id) {
        return holidayService.findHoliday(id);
    }

    @RequestMapping(value = "/api/types", method = RequestMethod.GET)
    public List<HolidayType> holidayTypes() {
        return holidayService.findHolidayTypes();
    }

    //@RequestMapping(value = "/api/holidays/{id}", method = RequestMethod.GET)

}
