package com.carpool.controller;

import com.carpool.domain.User;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Crawlers on 4/24/2017.
 */
@Controller
@SessionAttributes({"LoggedUser"})
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView loginForm(){
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(ModelMap model, @RequestParam String username, @RequestParam String password){
        //User dbUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        User dbUser = userRepository.findByEmailAndPassword(username, password);
        System.out.println("Db User:"+dbUser);
        ModelAndView modelAndView = new ModelAndView();
        if (dbUser==null){
            modelAndView.addObject("errorMsg", "Username or password is incorrect");
            modelAndView.setViewName("login");
            return modelAndView;
        }else {
            model.addAttribute("LoggedUser",username);
            return new ModelAndView("redirect:/home", model);
        }
    }

    @GetMapping("/signup")
    public ModelAndView signupForm(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signupPost(){
        return new ModelAndView("signup");
    }
}
