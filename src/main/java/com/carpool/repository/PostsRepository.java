package com.carpool.repository;

import com.carpool.domain.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Crawlers on 4/24/2017.
 */
public interface PostsRepository extends MongoRepository<Posts, String> {
    public Posts findByPostId(String postId);
    public List<Posts> findByUserId(String userId);
    public Posts findByDatecreated(LocalDateTime datecreated);
    public Posts findByDateupdated(LocalDateTime dateupdated);
    public List<Posts> findByPostType(String postType);
    public List<Posts> findByUserIdAndPostType(String userId, String postType);
    public List<Posts> findAll();
   // public Posts insert(Posts posts);
    public void removePostsByPostId(String id);
//    public void updatePosts(Posts posts);
//    void insertPosts(Posts posts);

}
