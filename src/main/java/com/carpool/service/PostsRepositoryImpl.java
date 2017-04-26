package com.carpool.service;

import com.carpool.domain.Posts;
import com.carpool.repository.PostsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by bi on 4/25/2017.
 */
public class PostsRepositoryImpl implements PostsRepositoryCustom{

    private final MongoOperations operations;

    @Autowired
    public PostsRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations must not be null!");
        this.operations = operations;
    }

    public List<Posts> getRange(String fromId, String toId){

        //where("age").lt(40).andOperator(Criteria.where("age").gt(10))
//        query4.addCriteria(Criteria.
//
//        List<Posts> userTest4 = operations.find(query4, Posts.class);
//        System.out.println("query4 - " + query4.toString());
//
//        for (Posts user : userTest4) {
//            System.out.println("userTest4 - " + user);
//        }

        final Pageable pageableRequest = new PageRequest(Integer.parseInt(fromId), Integer.parseInt(toId));
        Query query = new Query();
        query.with(pageableRequest);
        List<Posts> posts = operations.find(query,Posts.class);
        return posts;
    }

    public List<Posts> findByPostTypeRange(String postType, String fromId, String toId){

        final Pageable pageableRequest2 = new PageRequest(Integer.parseInt(fromId), Integer.parseInt(toId));
//        final Pageable pageableRequest2 = new PageRequest(0, 2);
        Query query = new Query();
        query.addCriteria(Criteria.where("postType").in(postType));
        query.with(pageableRequest2);

        query.with(new Sort(Sort.Direction.DESC, "postId"));
        List<Posts> posts = operations.find(query,Posts.class);
        return posts;
    }

    public List<Posts> findByUserIdPostTypeRange(String userId, String postType, String fromId, String toId){

        final Pageable pageableRequest2 = new PageRequest(Integer.parseInt(fromId), Integer.parseInt(toId));
//        final Pageable pageableRequest2 = new PageRequest(0, 2);
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").in(userId).andOperator(Criteria.where("postType").in(postType)));
        query.with(pageableRequest2);

        query.with(new Sort(Sort.Direction.DESC, "postId"));
        List<Posts> posts = operations.find(query,Posts.class);
        return posts;
    }
}
