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
import com.carpool.validator.Validator;
import java.util.Collection;

/**
 * Created by Crawlers on 4/24/2017.
 */
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    Validator<Posts> validator;

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

    //@RequestMapping(value = "/userIdpostType/{userId}{postType}", method = RequestMethod.GET)
//    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
//    public Collection<Posts> findByUserId(
//            @PathVariable("userId") String userId){
//        return postsRepository.findByUserId(userId);
//    }


    @RequestMapping(value = "/users/{userId}/posts/{postType}", method = RequestMethod.GET)
    public Collection<Posts> findByUserIdAndPostType(
            @PathVariable("userId") String userId,
            @PathVariable("postType") String postType){
        return postsRepository.findByUserIdAndPostType(userId,postType);
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

}
