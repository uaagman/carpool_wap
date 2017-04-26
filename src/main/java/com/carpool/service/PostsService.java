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
    public Long countDocument(){
        return this.postsRepository.count();
    }

    public Collection<Posts> findbyPostIdRange(String fromId,String toId){
        return this.postsRepository.findbyPostIdRange(fromId,toId);
    }
    public Collection<Posts> findByPostIdBetween(String fromId,String toId){
        return this.postsRepository.findByPostIdBetween(fromId,toId);
    }
//
//    public void updatePosts(Posts student){
//        this.postsRepository.updatePosts(student);
//    }

//    public void insertPosts(Posts student) {
//        this.postsRepository.insertPosts(student);
//    }

}
