package com.problemdetail.user.controller;

import com.problemdetail.user.entity.User;
import com.problemdetail.user.exception.UserNotFoundException;
import com.problemdetail.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        var result = userService.getUserById(id);
        if (result.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(result.get());
    }
}
