package edu.hawaii.its.holiday.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidayRestController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);

    @RequestMapping(value = "/api/holidays", method = RequestMethod.GET)
    public List<String> holidays() {
        logger.info("Entered holidays...");

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("string-" + i);
        }

        logger.info("Leaving holidays.");

        return list;
    }

}
