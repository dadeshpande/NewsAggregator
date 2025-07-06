package com.mynews.newsaggregator.controller;

import com.mynews.newsaggregator.entity.Subscribe;
import com.mynews.newsaggregator.exception.SubscribeNotFoundException;

import com.mynews.newsaggregator.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService; // Assuming a service layer exists for subscribes

    @GetMapping("/subscribes")
    public List<Subscribe> getAllSubscribes() {
        return subscribeService.getAllSubscribes();
    }

    @GetMapping("/subscribes/{id}")
    public Subscribe getSubscribesById(@RequestParam Long id) {
        return subscribeService.getSubscribesById(id);
    }

    @PostMapping("/subscribes")
    public Subscribe addSubscribe(@RequestBody Subscribe subscribe) {
        return subscribeService.addSubscribe(subscribe); // Assuming a method to add subscribes exists
    }

    @PutMapping("/subscribes/{id}")
    public Subscribe updateSubscribe(@RequestParam Long id, @RequestBody Subscribe subscribe) throws SubscribeNotFoundException {
        return subscribeService.updateSubscribe(id, subscribe); // Reusing addSubscribe for update
    }

    @DeleteMapping("/subscribes/{id}")
    public ResponseEntity<String> deleteSubscribe(@PathVariable Long id) throws SubscribeNotFoundException {
        return subscribeService.deleteSubscribe(id); // Assuming a method to delete subscribes exists
    }

    @ExceptionHandler(SubscribeNotFoundException.class)
    public ResponseEntity handleSubscribeNotFound(SubscribeNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body("Subscribe not found: " + ex.getMessage());
    }
}
