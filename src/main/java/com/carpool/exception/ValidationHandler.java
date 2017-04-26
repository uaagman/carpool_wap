package com.carpool.exception;

import org.springframework.web.servlet.ModelAndView;

public class ValidationHandler {

  public static ModelAndView showError(String msg, String page){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("errorMsg", msg);
    modelAndView.setViewName(page);
    return modelAndView;
  }

}
