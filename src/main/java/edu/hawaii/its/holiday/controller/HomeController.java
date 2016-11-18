package edu.hawaii.its.holiday.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hawaii.its.holiday.access.User;
import edu.hawaii.its.holiday.security.UserContextService;
import edu.hawaii.its.holiday.service.HolidayService;
import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.type.YearHolidayHolder;
import edu.hawaii.its.holiday.util.Dates;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private UserContextService userContextService;

    @RequestMapping(value = { "", "/", "/home" }, method = { RequestMethod.GET })
    public String main(Locale locale) {
        logger.info("User at main. The client locale is {}.", locale);
        return "main";
    }

    @PreAuthorize("hasRole('ROLE_UH')")
    @RequestMapping(value = { "/attributes" }, method = { RequestMethod.GET })
    public String attributes(Locale locale, Model model) {
        logger.info("Entered attributes...");

        User user = userContextService.getCurrentUser();
        logger.info("current user    : " + user);
        model.addAttribute("currentUser", user);

        logger.info("Leaving attributes.");

        return "attributes";
    }

    @ModelAttribute("holidayHolder")
    public YearHolidayHolder createYearHolidayHolder(Integer year) {
        List<Holiday> list = holidayService.findHolidays();

        YearHolidayHolder holder = new YearHolidayHolder(list);
        if (year != null) {
            holder.setYear(year);
        }

        return holder;
    }

    protected Integer yearValue(BindingResult result) {
        Integer year;
        try {
            String yearStr = (String) result.getFieldValue("year");
            year = Integer.valueOf(yearStr);
        } catch (Exception ex) {
            year = Integer.valueOf(Dates.currentYear());
        }
        return year;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Locale locale, Model model) {
        return "contact";
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public String faq(Locale locale, Model model) {
        return "faq";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String denied() {
        return "denied";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String invalid() {
        return "redirect:/";
    }

    public void setUserContextService(UserContextService userContextService) {
        this.userContextService = userContextService;
    }
}
