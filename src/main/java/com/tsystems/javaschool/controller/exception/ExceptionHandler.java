package com.tsystems.javaschool.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice(basePackages = "com.tsystems.javaschool.controller")
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handlePageNotFoundError(NoHandlerFoundException e) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", e);
        model.addObject("errorMessage", "Error 404: Requested page not found.");
        return model;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleOtherException(Exception e) {
        ModelAndView model = new ModelAndView("error");
        log.error("Unexpected server error", e);
        model.addObject("exception", e);
        model.addObject("errorMessage", "Sorry! An unexpected technical error occurred. Please try again later.");
       e.printStackTrace();
        return model;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionTreatmentNotFound.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handlTreatmentNotFoundException(){
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", "Treatment not found");
        model.addObject("errorMessage", "This treatment has not yet been created or has already been deleted");
        return model;
    }

}
