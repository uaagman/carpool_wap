package com.carpool.exception;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mukesh Maharjan on 12/24/2015.
 * Redirect all Validation Error here
 */
public class ValidationHandler {

  public static ModelAndView showError(String msg, String page){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("errorMsg", msg);
    modelAndView.setViewName(page);
    return modelAndView;
  }

}
