package com.carpool.repository;

import com.carpool.domain.Posts;

import java.util.List;

/**
 * Created by bi on 4/25/2017.
 */
public interface PostsRepositoryCustom {
    public List<Posts> getRange(String fromId, String toId);
    public List<Posts> findByPostTypeRange(String postType, String fromId, String toId);
    public List<Posts> findByUserIdPostTypeRange(String userId, String postType, String fromId, String toId);
}
