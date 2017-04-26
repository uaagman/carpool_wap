package com.carpool.controller;

import com.carpool.domain.Comment;
import com.carpool.domain.Like;
import com.carpool.domain.Posts;
import com.carpool.domain.User;
import com.carpool.repository.CommentRepository;
import com.carpool.repository.LikeRepository;
import com.carpool.repository.PostsRepository;
import com.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashok on 4/25/2017.
 */
@RestController
@RequestMapping("/js")
public class ProjectRestController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    PostsRepository postsRepository;

    @GetMapping("/getZipOfLoggedUser")
    public Integer getZipOfLoggedUser(HttpSession session) {
        String email = (String) session.getAttribute("loggedUser");
        if (email == null) {
            return 0;
        }
        return userRepository.findByEmail(email).getZipCode();
    }

    @GetMapping("/getEmailAndNameFromId/{id}")
    public Map<String, String> getEmailAndNameFromId(@PathVariable("id") String id) {
        User user = userRepository.findByUserId(id);
        if (user == null) {
            return null;
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("email", user.getEmail());
            map.put("fullName", user.getFullName());
            return map;
        }
    }

    @GetMapping("/getLnC/{postId}")
    public Map<String, Object> getLnC(@PathVariable("postId") String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<Like> likes = likeRepository.findByPostId(postId);
        Map<String, Object> map = new HashMap<>();
        map.put("comments", comments);
        map.put("likes", likes.size());
        return map;
    }

    @PostMapping("/addComment")
    public Map<String, Object> addComment(@RequestParam String postId, @RequestParam String comment, HttpSession session) {
        String email = (String) session.getAttribute("loggedUser");
        User user = userRepository.findByEmail(email);
        Comment comment1 = new Comment(user.getUserId(), postId, comment);
        Comment comment2 = commentRepository.insert(comment1);

        Map<String, String> map1 = new HashMap<>();
        map1.put("fullName", user.getFullName());
        map1.put("email", user.getEmail());
        Map<String, Object> map = new HashMap<>();
        map.put("comment", comment2);
        map.put("user", map1);
        return map;
    }

    @PostMapping("/addLike")
    public Map<String, Integer> addLike(@RequestParam String postId, HttpSession session) {
        String email = (String) session.getAttribute("loggedUser");
        User user = userRepository.findByEmail(email);

        Like like1 = likeRepository.findByPostIdAndUserId(postId, user.getUserId());
        Map<String, Integer> map = new HashMap<>();
        if (like1 == null) {
            likeRepository.insert(new Like(user.getUserId(), postId, LocalDateTime.now()));
        } else {
            map.put("errorCode", 1);
        }
        map.put("likeCount", likeRepository.findByPostId(postId).size());
        return map;
    }


    @GetMapping(value = "/postType/{postType}/{page}/{size}")
    public Collection<Posts> findByPostTypeBySize(@PathVariable("postType") String postType, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return postsRepository.findByPostTypeOrderByDatecreatedDesc(postType,new PageRequest(page,size));
    }
}
