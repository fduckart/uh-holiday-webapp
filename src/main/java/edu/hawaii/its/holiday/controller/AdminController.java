package edu.hawaii.its.holiday.controller;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hawaii.its.holiday.service.ActionService;

@Controller
public class AdminController {

    @Autowired
    private ActionService actionService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Locale locale, Model model) {
        return "admin";
    }

    @RequestMapping(value = "/admin/actions", method = RequestMethod.GET)
    public String listActions(Model model) {
        model.addAttribute(actionService.findActions());
        return "actions/list"; // Return view name.
    }

    ///////////////////////////////////////////////////////////////////////////
    // Exceptions for testing. Remove this stuff at some point.
    //

    private boolean isExceptionTestingEnabled() {
        return true;
    }

    @RequestMapping(value = "/admin/ioexception", method = RequestMethod.GET)
    public String ioexception(Model model) throws IOException {
        if (isExceptionTestingEnabled()) {
            throw new IOException("Threw an IOException from admin!");
        }
        return "exception"; // Return view name.
    }

    @RequestMapping(value = "/admin/npe", method = RequestMethod.GET)
    public String npe(Model model) throws NullPointerException {
        if (isExceptionTestingEnabled()) {
            throw new NullPointerException("Threw an NullPointerException from admin!");
        }
        return "exception"; // Return view name.
    }

}
