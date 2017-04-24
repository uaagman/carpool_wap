package com.carpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ashok on 4/24/2017.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView home(){
        System.out.println("/ here");
        return new ModelAndView("home");
    }
    @RequestMapping("/home")
    public ModelAndView h(){
        System.out.println("/home here");
        return new ModelAndView("home");
    }
}
