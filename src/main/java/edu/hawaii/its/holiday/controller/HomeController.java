package edu.hawaii.its.holiday.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hawaii.its.holiday.access.User;
import edu.hawaii.its.holiday.security.UserContextService;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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

}
