package com.crowdcollective.spring_client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView displayError(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        logger.error("", e);
        mav.addObject("exceptionMessage", e.getClass().getName() + ": " + e.getMessage());
        mav.addObject("exception", e);
        mav.addObject("url", "Failed URL: " + req.getRequestURL());
        mav.setViewName("error");

        return mav;
    }
}
