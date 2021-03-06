package com.carpool.controller;

import com.carpool.domain.User;
import com.carpool.exception.ErrorMessage;
import com.carpool.repository.UserRepository;
import com.carpool.validator.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Crawlers on 4/24/2017.
 */
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator<User> validator;

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(ModelMap model, @RequestParam String username, @RequestParam String password, HttpSession session) {
        //User dbUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        User dbUser = userRepository.findByEmailAndPassword(username, password);
//        System.out.println("Db User:" + dbUser);
        ModelAndView modelAndView = new ModelAndView();
        if (dbUser == null) {
            modelAndView.addObject("errorMsg", "Username or password is incorrect");
            modelAndView.setViewName("login");
            return modelAndView;
        } else {
            //session.addAttribute("loggedUser",username);
            modelAndView.addObject("errorMsg", null);
            session.setAttribute("loggedUser", username);
            return new ModelAndView("redirect:/home", model);
        }
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signupPost(@RequestParam String userId,
                                   @RequestParam String fullName,
                                   @RequestParam String gender,
                                   @RequestParam String state,
                                   @RequestParam String city,
                                   @RequestParam String street,
                                   @RequestParam Integer zipCode,
                                   @RequestParam Integer birthYear,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String rePassword,
                                   ModelMap model, HttpSession session) {
        User user = new User(userId, fullName, gender, state, city, street, zipCode, birthYear, email, password);
        Map<Boolean, List<ErrorMessage>> validationMap = validator.validate(user);
        if ((boolean)validationMap.keySet().toArray()[0]) {
            if (password.equals(rePassword)){
                if (userId==null||userId.isEmpty()){
                    user.setUserId(null);
                    userRepository.insert(user);
                }else {
                    userRepository.save(user);
                }
                session.setAttribute("loggedUser", email);
                return new ModelAndView("redirect:/home", model);
            }else {
                model.addAttribute("errorMsg", "Password Confirmation didn't matched!!!");
            }
        } else {
            model.addAttribute("errors", validationMap.get(validationMap.keySet().toArray()[0]));
        }
        model.addAttribute("user", user);
        return new ModelAndView("signup");
    }

    @GetMapping("/profile")
    public ModelAndView profile(ModelMap model, HttpSession httpSession){
        User user = userRepository.findByEmail((String)httpSession.getAttribute("loggedUser"));
        model.addAttribute("user", user);
        return new ModelAndView("signup");
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelMap model, HttpSession session) {
        session.removeAttribute("loggedUser");
        session.invalidate();
        return new ModelAndView("redirect:/home", model);
    }

    @GetMapping("/getZipOfLoggedUser")
    public Integer getUserByEmail(HttpSession session){
        String email = (String) session.getAttribute("loggedUser");
        if(email == null){
            return 0;
        }
        return userRepository.findByEmail(email).getZipCode();
    }

}
