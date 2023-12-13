package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.domain.UserPrincipal;
import com.example.backend.exception.domain.EmailExistsException;
import com.example.backend.exception.domain.UserNotFoundException;
import com.example.backend.exception.domain.UsernameExistsException;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        when(userRepository.findAll()).thenReturn(Collections.singletonList(new User()));
        List<User> users = userService.getUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void FindUserByUsername_ReturnsUser() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(new User());
        User userByUsername = userService.findUserByUsername("Test");
        assertNotNull(userByUsername);
    }

    @Test
    public void FindUserByEmail_ReturnsUser() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new User());
        User userByEmail = userService.findUserByEmail("Test");
        assertNotNull(userByEmail);
    }

    @Test
    public void loadUserByUsername_UserFound_ReturnsUserPrincipal() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(sharedUser);
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername("testUser");
        assertNotNull(userPrincipal);
        assertEquals(sharedUser.getUsername(), userPrincipal.getUsername());
    }

    @Test
    public void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistingUser"));
    }
}