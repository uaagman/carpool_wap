package com.carpool.domain;

import com.carpool.validator.constraint.Length;
import com.carpool.validator.constraint.Number;
import org.springframework.data.annotation.Id;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Crawlers on 4/24/2017.
 */
public class Posts {
    @Id
    private String postId;
    private String userId;
    private String post;
    private String postType;

//    private Timestamp datecreated = instant.toEpochMilli();
//    private Timestamp dateupdated = new Timestamp(date.getTime());


    private LocalDateTime datecreated = LocalDateTime.now();
    private LocalDateTime dateupdated = LocalDateTime.now();
    private LocalDateTime duedate;// = LocalDateTime.now(;

    private String fromCity;
    private String fromState;
    @Number
    @Length(min = 5, max = 5)
    private String fromZip;

    private String toCity;
    private String toState;
    @Number
    @Length(min = 5, max = 5)
    private String toZip;

    public Posts(){

    }

    public Posts( String userId, String post, String postType, LocalDateTime datecreated, LocalDateTime dateupdated, LocalDateTime duedate, String fromCity, String fromState, String fromZip, String toCity, String toState, String toZip) {

        this.userId = userId;
        this.post = post;
        this.postType = postType;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
        this.duedate = duedate;
        this.fromCity = fromCity;
        this.fromState = fromState;
        this.fromZip = fromZip;
        this.toCity = toCity;
        this.toState = toState;
        this.toZip = toZip;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public LocalDateTime getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(LocalDateTime datecreated) {
        this.datecreated = datecreated;
    }

    public LocalDateTime getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(LocalDateTime dateupdated) {
        this.dateupdated = dateupdated;
    }

    public LocalDateTime getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDateTime duedate) {
        this.duedate = duedate;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getFromZip() {
        return fromZip;
    }

    public void setFromZip(String fromZip) {
        this.fromZip = fromZip;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public String getToZip() {
        return toZip;
    }

    public void setToZip(String toZip) {
        this.toZip = toZip;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", post='" + post + '\'' +
                ", postType='" + postType + '\'' +
                ", datecreated=" + datecreated +
                ", dateupdated=" + dateupdated +
                ", duedate=" + duedate +
                ", fromCity='" + fromCity + '\'' +
                ", fromState='" + fromState + '\'' +
                ", fromZip='" + fromZip + '\'' +
                ", toCity='" + toCity + '\'' +
                ", toState='" + toState + '\'' +
                ", toZip='" + toZip + '\'' +
                '}';
    }
}
