package com.carpool.controller;

import com.carpool.domain.Posts;
import com.carpool.domain.User;
import com.carpool.repository.PostsRepository;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.carpool.validator.Validator;
import java.util.Collection;
import java.util.List;

/**
 * Created by Crawlers on 4/24/2017.
 */
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

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
        return postsRepository.findByPostType(postType,new PageRequest(1,1));
    }

    @RequestMapping(value = "/postTypeRange/{postType}/fromrange/{fromId}/torange/{toId}", method = RequestMethod.GET)
    public Collection<Posts> findByPostTypeRange(
            @PathVariable("postType") String postType,
            @PathVariable("fromId") String fromId,
            @PathVariable("toId") String toId){
        return postsRepository.findByPostTypeRange(postType,fromId,toId);
    }

    @RequestMapping(value = "usertyperange/{userId}/postTypeRange/{postType}/fromrange/{fromId}/torange/{toId}", method = RequestMethod.GET)
    public Collection<Posts> findByUserIdPostTypeRange(
            @PathVariable("userId") String userId,
            @PathVariable("postType") String postType,
            @PathVariable("fromId") String fromId,
            @PathVariable("toId") String toId){
        User user= userRepository.findByEmail(userId);
        if(user!=null){
            return postsRepository.findByUserIdPostTypeRange(user.getUserId(),postType,fromId,toId);
        }
        else
            return null;

    }


    @RequestMapping(value = "/users/{userId}/posts/{postType}", method = RequestMethod.GET)
    public Collection<Posts> findByUserIdAndPostType(
            @PathVariable("userId") String userId,
            @PathVariable("postType") String postType){
        User user= userRepository.findByEmail(userId);
        if(user!=null){
            return postsRepository.findByUserIdAndPostType(user.getUserId(),postType);
        }
        else
            return null;
    }


    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return postsRepository.count();
    }

    @RequestMapping(value = "/fromrange/{fromId}/torange/{toId}", method = RequestMethod.GET)
    public Collection<Posts> getRange(
            @PathVariable("fromId") String fromId,
            @PathVariable("toId") String toId
    ){
        return postsRepository.getRange(fromId,toId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePostsById(@PathVariable("id") String id){
        postsRepository.removePostsByPostId(id);

    }
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
