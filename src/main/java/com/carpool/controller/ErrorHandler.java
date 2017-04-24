package com.carpool.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ashok on 4/24/2017.
 */
@Controller
public class ErrorHandler implements ErrorController {
    private static final String PATH = "/error";

    @GetMapping(PATH)
    public ModelAndView error(){
        return new ModelAndView(PATH);
    }
    @Override
    public String getErrorPath() {
        return PATH;
    }
}
