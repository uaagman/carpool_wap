package com.carpool.controller;

import com.carpool.domain.Posts;
import com.carpool.repository.PostsRepository;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import com.carpool.domain.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.carpool.validator.Validator;

/**
 * Created by Crawlers on 4/24/2017.
 */

@Controller
public class PostsManupulationController {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator<Posts> validator;

    @GetMapping("/newpost")
    public ModelAndView postForm(HttpSession session){
        session.setAttribute("errorMsg",null);

        return new ModelAndView("newpost");
    }

    @PostMapping("/newpost")
    public ModelAndView postNew(
            @RequestParam String post,
            @RequestParam String postType,
            @RequestParam String dueDate,
            @RequestParam String fromCity,
            @RequestParam String fromState,
            @RequestParam String fromZip,
            @RequestParam String toCity,
            @RequestParam String toState,
            @RequestParam String toZip,
            ModelMap model, HttpSession session){
        session.setAttribute("errorMsg",null);
        String userId=(String)session.getAttribute("loggedUser");
        if(userId==null)
        {
            session.setAttribute("errorMsg","Not authorized user");
            return new ModelAndView("redirect:/login", model);
        }
        if(!"pool".equals(postType) && !"share".equals(postType)){
            System.out.println("Warning post manipulation");
            return new ModelAndView("redirect:/home", model);
        }
        //04/06/2017 1:51 PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");
        LocalDateTime dDate=  LocalDateTime.parse(dueDate, formatter);
        //Posts posts1 = new Posts(userId,post,pType,LocalDateTime.now(),LocalDateTime.now());
        User user= userRepository.findByEmail(userId);
        Posts posts = new Posts( user.getUserId(), post, postType, LocalDateTime.now(),LocalDateTime.now(), dDate, fromCity, fromState , fromZip, toCity , toState, toZip );


        if (validator.validate(posts)) {
            posts = postsRepository.insert(posts);
            System.out.println("New Posts:"+posts);
            return new ModelAndView("redirect:/home", model);

        } else {
            model.addAttribute("errorMsg", "User data posted is invalid");
            return new ModelAndView("newpost");
        }
    }

    @GetMapping("/myposts")
    public ModelAndView mypostsForm(){
        return new ModelAndView("myposts");
    }


}
