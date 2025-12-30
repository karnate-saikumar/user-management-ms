package com.kuber.user.main.service;

import com.kuber.user.main.entity.User;
import com.kuber.user.main.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    public User removeUser(User user) {
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            return findUserById(user.getId());
        }
        return findUserById(user.getId());
    }
}
