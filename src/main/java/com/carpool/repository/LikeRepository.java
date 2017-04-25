package com.carpool.repository;

import com.carpool.domain.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ashok on 4/25/2017.
 */
public interface LikeRepository extends MongoRepository<Like, String> {
    public List<Like> findByPostId(String postId);
}
