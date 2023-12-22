package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.exception.domain.EmailExistsException;
import com.example.backend.exception.domain.EmailNotFoundException;
import com.example.backend.exception.domain.UserNotFoundException;
import com.example.backend.exception.domain.UsernameExistsException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistsException, EmailExistsException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNotLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException;
    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNotLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException;
    void deleteUser(Long id);
    void resetPassword(String email) throws EmailNotFoundException, MessagingException;
    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistsException, EmailExistsException, IOException;
}
