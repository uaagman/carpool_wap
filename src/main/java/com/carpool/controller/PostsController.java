package com.carpool.controller;

import com.carpool.domain.Posts;
import com.carpool.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Crawlers on 4/24/2017.
 */
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Posts> findAll(){
        return postsRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Posts findByPostId(@PathVariable("id") String id){
        return postsRepository.findByPostId(id);
    }

    @RequestMapping(value = "/postType/{postType}", method = RequestMethod.GET)
    public Collection<Posts> findByPostType(@PathVariable("postType") String postType){
        return postsRepository.findByPostType(postType);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deletePostsById(@PathVariable("id") String id){
//        postsRepository.removePostsByPostId(id);
//
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void deletePostsById(@RequestBody Posts posts){
//        postsRepository.updatePosts(posts);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void insertPosts(@RequestBody Posts posts){
//
//        postsRepository.insertPosts(posts);
//    }
//    @GetMapping("/posts")
//    public ModelAndView loginForm(){
//        return new ModelAndView("posts");
//    }

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

        LocalDate dDate=  LocalDate.now();
        //Posts posts1 = new Posts(userId,post,pType,LocalDateTime.now(),LocalDateTime.now());
        Posts posts = new Posts( userId, post, postType, LocalDateTime.now(),LocalDateTime.now(), dDate, fromCity, fromState , fromZip, toCity , toState, toZip );
        posts = postsRepository.insert(posts);
        System.out.println("New Posts:"+posts);
        return new ModelAndView("redirect:/home", model);
    }


}
