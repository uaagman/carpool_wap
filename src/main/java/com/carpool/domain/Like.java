package com.carpool.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Created by ashok on 4/25/2017.
 */
public class Like {
    @Id
    private String likeId;
    private String userId;
    private String postId;
    private LocalDateTime dateCreated = LocalDateTime.now();

    public Like(String userId, String postId, LocalDateTime dateCreated) {
        this.userId = userId;
        this.postId = postId;
        this.dateCreated = dateCreated;
    }

    public Like() {
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
