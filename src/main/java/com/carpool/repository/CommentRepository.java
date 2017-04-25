package com.carpool.repository;

import com.carpool.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ashok on 4/25/2017.
 */
public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findByPostId(String postId);
}
