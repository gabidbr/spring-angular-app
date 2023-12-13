package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.exception.domain.EmailExistsException;
import com.example.backend.exception.domain.UserNotFoundException;
import com.example.backend.exception.domain.UsernameExistsException;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistsException, EmailExistsException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
