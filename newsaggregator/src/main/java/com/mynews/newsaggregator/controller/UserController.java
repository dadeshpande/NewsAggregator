package com.myuser.useraggregator.controller;

import com.mynews.newsaggregator.entity.User;
import com.mynews.newsaggregator.entity.UserDTO;
import com.mynews.newsaggregator.exception.UserNotFoundException;
import com.mynews.newsaggregator.service.AuthenticationService;
import com.myuser.useraggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService; // Assuming a service layer exists for users

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUsersById(@RequestParam Long id) {
        return userService.getUsersById(id);
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user); // Assuming a method to add users exists
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestParam Long id, @RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(id, user); // Reusing addUser for update
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.deleteUser(id); // Assuming a method to delete users exists
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body("User not found: " + ex.getMessage());
    }

    @Autowired
    private AuthenticationService _authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        User registeredUser = _authenticationService.registerUser(userDTO);
        String generatedToken = UUID.randomUUID().toString();
        String applicationUrl = "http://localhost:8080/verifyRegistration?token=" + generatedToken;
        System.out.println("Verify your identity by clicking on the following url: " + applicationUrl);
        _authenticationService.persistRegistrationToken(registeredUser, generatedToken);
        return registeredUser;
    }

    @PostMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        boolean isTokenValid = _authenticationService.verifyRegistrationToken(token);
        if (isTokenValid) {
            _authenticationService.enableUser(token);
            return "Token verification successful, user enabled. Please login to proceed";
        } else {
            return "Token verification failed";
        }
    }

    @PostMapping("/signin")
    public String signin(@RequestParam("username") String username, @RequestParam("password") String password) {
        return _authenticationService.signinUser(username, password);
    }

    @PostMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String test() {
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        System.out.println("User authorities: " + authorities);
        System.out.println("Authenticated user: " + username);
        return "Test successful, authenticated user: " + username;
    }
}
