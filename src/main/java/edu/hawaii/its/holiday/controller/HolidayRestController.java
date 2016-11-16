package edu.hawaii.its.holiday.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.hawaii.its.holiday.service.HolidayService;
import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.type.Type;

@RestController
public class HolidayRestController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);

    @Autowired
    private HolidayService holidayService;

    @RequestMapping(value = "/api/holidays",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Holiday>> holidays() {
        logger.info("Entered REST holidays...");
        return ResponseEntity
                .ok()
                .body(holidayService.findHolidays());
    }

    @RequestMapping(value = "/api/holidays/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Holiday> holiday(@PathVariable Integer id) {
        logger.info("Entered REST holiday(" + id + ") ...");
        return ResponseEntity
                .ok()
                .body(holidayService.findHoliday(id));
    }

    @RequestMapping(value = "/api/types",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Type>> types() {
        logger.info("Entered REST types...");
        List<Type> types = holidayService.findTypes();
        return ResponseEntity
                .ok()
                .body(types);
    }

}
