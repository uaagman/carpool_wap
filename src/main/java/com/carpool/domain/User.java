package com.carpool.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by Crawlers on 4/24/2017.
 */
public class User {
    @Id
    private String userId;
    private String fullname;
    private String gender;
    private String state;
    private String city;
    private String street;
    private Integer zipCode;
    private Integer birthYear;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId='" + userId + '\'' +
            ", fullname='" + fullname + '\'' +
            ", gender='" + gender + '\'' +
            ", state='" + state + '\'' +
            ", city='" + city + '\'' +
            ", street='" + street + '\'' +
            ", zipCode=" + zipCode +
            ", birthYear=" + birthYear +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
