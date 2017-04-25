package com.carpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ashok on 4/24/2017.
 */
@Controller
public class WeatherController {
    @GetMapping("/weather")
    public ModelAndView weather(){
        return new ModelAndView("weather");
    }
}
