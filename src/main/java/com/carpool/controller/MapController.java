package com.carpool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Crawlers on 4/26/2017.
 */
@Controller
public class MapController {
    @GetMapping("/map")
    public ModelAndView map(){
        return new ModelAndView("/map");
    }

}
