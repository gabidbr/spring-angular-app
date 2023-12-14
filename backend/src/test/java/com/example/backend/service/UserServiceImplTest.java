package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.domain.UserPrincipal;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User sharedUser;

    @BeforeEach
    public void setUp() {
        // Shared user for testing
        sharedUser = new User();
        sharedUser.setId(1L);
        sharedUser.setUsername("testUser");
        sharedUser.setEmail("test@example.com");
    }


    @Test
    public void GetUsers_ReturnsListOfUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(sharedUser));
        List<User> users = userService.getUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void FindUserByUsername_ReturnsUser() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(sharedUser);
        User userByUsername = userService.findUserByUsername("testUser");
        assertNotNull(userByUsername);
        assertEquals("testUser", sharedUser.getUsername());
    }

    @Test
    public void FindUserByEmail_ReturnsUser() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new User());
        User userByEmail = userService.findUserByEmail("testUser");
        assertNotNull(userByEmail);
        assertEquals(1L, sharedUser.getId());
    }

    @Test
    public void LoadUserByUsername_UserFound_ReturnsUserPrincipal() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(sharedUser);
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername("testUser");
        assertNotNull(userPrincipal);
        assertEquals(sharedUser.getUsername(), userPrincipal.getUsername());
    }

    @Test
    public void LoadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistingUser"));
    }

//    @Test
//    public void Register_CreatesNewUser() throws UserNotFoundException, UsernameExistsException, EmailExistsException {
//        String firstName = "John";
//        String lastName = "Doe";
//        String username = "johndoe";
//        String email = "johndoe@example.com";
//        when(userRepository.findUserByUsername(username)).thenReturn(null);
//        when(userRepository.findByEmail(email)).thenReturn(null);
//
//        User user = userService.register(firstName, lastName, username, email);
//
//        assertNotNull(user);
//        assertEquals(firstName, user.getFirstName());
//        assertEquals(lastName, user.getLastName());
//        assertEquals(username, user.getUsername());
//        assertEquals(email, user.getEmail());
//    }
}