package com.carpool.domain;

import com.carpool.validator.constraint.Number;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

/**
 * Created by Crawlers on 4/24/2017.
 */
public class User {
    @Id
    private String userId;
    private String fullName;
    private String gender;
    private String state;
    private String city;
    private String street;
    @Number
    private Integer zipCode;
    @Number
    private Integer birthYear;
    @Email
    private String email;
    private String password;

    public User(){

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String fullName, String gender, String state, String city, String street, Integer zipCode, Integer birthYear, String email, String password) {
        this.fullName = fullName;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.birthYear = birthYear;
        this.email = email;
        this.password = password;
    }

    public User(String userId, String fullName, String gender, String state, String city, String street, Integer zipCode, Integer birthYear, String email, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.birthYear = birthYear;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
            ", fullName='" + fullName + '\'' +
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
