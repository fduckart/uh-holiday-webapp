package edu.hawaii.its.holiday.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import edu.hawaii.its.holiday.access.User;
import edu.hawaii.its.holiday.security.UserContextService;

@ControllerAdvice
public class ErrorControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @Autowired
    private UserContextService userContextService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        return errorModelAndView(ex);
    }

    private ModelAndView errorModelAndView(Exception ex) {
        String username = null;
        User user = userContextService.getCurrentUser();
        if (user != null) {
            username = user.getUsername();
        }
        logger.error("username: " + username + "; Exception: " + ex);

        return new ModelAndView("redirect:/");
    }

}