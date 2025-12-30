package com.kuber.user.main.controller;

import com.kuber.user.main.entity.User;
import com.kuber.user.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getBookById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody User book) {
        User user = userService.save(book);
        return new ResponseEntity<>(user != null ? user : "", user != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<User> updateBookById(@PathVariable Long id, @RequestBody User user) {
        com.kuber.user.main.entity.User userfromDb = userService.findUserById(id);
        if (userfromDb != null) {
            userfromDb.setName(user.getName());
            userfromDb.setDob(user.getEmail());
            userfromDb.setEmail(user.getEmail());
            User updated = userService.save(userfromDb);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
        com.kuber.user.main.entity.User userfromDb = userService.findUserById(id);
        if (userfromDb != null) {
            userService.removeUser(userfromDb);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
