package com.carpool.repository;

import com.carpool.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Crawlers on 4/24/2017.
 */
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserId(String userId);
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
    public List<User> findAll();
    public User insert(User user);
    public User save(User user);
}
