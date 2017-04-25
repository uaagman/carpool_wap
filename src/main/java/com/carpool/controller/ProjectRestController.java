package com.carpool.controller;

import com.carpool.domain.User;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashok on 4/25/2017.
 */
@RestController
@RequestMapping("/js")
public class ProjectRestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getZipOfLoggedUser")
    public Integer getZipOfLoggedUser(HttpSession session){
        String email = (String) session.getAttribute("loggedUser");
        if(email == null){
            return 0;
        }
        return userRepository.findByEmail(email).getZipCode();
    }

    @GetMapping("/getEmailAndNameFromId/{id}")
    public Map<String,String> getEmailAndNameFromId(@PathVariable("id") String id){
        User user = userRepository.findByUserId(id);
        if(user == null){
            return null;
        } else{
            Map<String,String> map = new HashMap<>();
            map.put("email",user.getEmail());
            map.put("fullName",user.getFullName());
            System.out.println(map);
            return map;
        }

    }
}
