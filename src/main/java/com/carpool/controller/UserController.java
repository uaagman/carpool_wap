package com.carpool.controller;

import com.carpool.domain.User;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Crawlers on 4/24/2017.
 */
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public ModelAndView loginForm(){
        List<User> list =userRepository.findAll();
        System.out.println("List:"+list);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signupForm(){

        return new ModelAndView("signup");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signupPost(){
        userRepository.insert(new User("mukes@gmail.com", "test"));
        return new ModelAndView("signup");
    }
}
