package com.carpool.controller;

import com.carpool.domain.Posts;
import com.carpool.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Crawlers on 4/24/2017.
 */
@Controller
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @GetMapping("/posts")
    public ModelAndView loginForm(){
        return new ModelAndView("posts");
    }

    @GetMapping("/newpost")
    public ModelAndView postForm(){
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
        //LocalDateTime.now()
        String userId=(String)session.getAttribute("LoggedUser");
        if(userId==null)
        {
            session.setAttribute("errorMsg","Not authorized user");
            return new ModelAndView("redirect:/login", model);
        }
        if(!"pool".equals(postType) && !"share".equals(postType)){
            System.out.println("Warning post manipulation");
            return new ModelAndView("redirect:/home", model);
        }

        LocalDate dDate=  LocalDate.now();
        //Posts posts1 = new Posts(userId,post,pType,LocalDateTime.now(),LocalDateTime.now());
        Posts posts = new Posts( userId, post, postType, LocalDateTime.now(),LocalDateTime.now(), dDate, fromCity, fromState , fromZip, toCity , toState, toZip );
        posts = postsRepository.insert(posts);
        System.out.println("New Posts:"+posts);
        return new ModelAndView("redirect:/posts", model);
    }


}
