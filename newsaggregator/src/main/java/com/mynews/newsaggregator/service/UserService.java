package com.myuser.useraggregator.service;


import com.mynews.newsaggregator.entity.User;
import com.mynews.newsaggregator.exception.UserNotFoundException;
import com.mynews.newsaggregator.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUsersById(Long id) {
        return userRepo.findById(id).orElse(null); // Example ID, replace with actual logic
    }

    public User addUser(User user) {
        return userRepo.save(user); // Assuming a method to save users exists
    }

    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> existingUser = userRepo.findById(id);
        if(!existingUser.isPresent()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        User updatedUser = existingUser.get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setSubscribe(user.getSubscribe());
        return userRepo.save(updatedUser);
    }

    public ResponseEntity<String> deleteUser(Long id) throws UserNotFoundException {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepo.deleteById(id);
        return ResponseEntity
                .status(200)
                .body("User deleted successfully");
    }
}
