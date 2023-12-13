package com.example.backend.resource;

import com.example.backend.domain.User;
import com.example.backend.exception.domain.EmailExistsException;
import com.example.backend.exception.domain.ExceptionHandling;
import com.example.backend.exception.domain.UserNotFoundException;
import com.example.backend.exception.domain.UsernameExistsException;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, UsernameExistsException, EmailExistsException {
        User newUser = userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
