package com.carpool.service;

import com.carpool.domain.Posts;
import com.carpool.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by bi on 4/25/2017.
 */
@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    
    public Collection<Posts> findAll(){
        return this.postsRepository.findAll();
    }

    public Collection<Posts> findByUserId(String id){
        return this.postsRepository.findByUserId(id);
    }

    public Collection<Posts> findByPostType(String postType){
        return this.postsRepository.findByPostType(postType);
    }

    public Collection<Posts> findByUserIdAndPostType(String userId,String postType){
        return this.postsRepository.findByUserIdAndPostType(userId,postType);
    }

    public void removePostsById(String id) {
        this.postsRepository.removePostsByPostId(id);
    }
//
//    public void updatePosts(Posts student){
//        this.postsRepository.updatePosts(student);
//    }

//    public void insertPosts(Posts student) {
//        this.postsRepository.insertPosts(student);
//    }

}
