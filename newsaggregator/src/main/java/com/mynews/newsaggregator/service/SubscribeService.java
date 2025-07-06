package com.mynews.newsaggregator.service;

import com.mynews.newsaggregator.entity.Subscribe;
import com.mynews.newsaggregator.exception.SubscribeNotFoundException;
import com.mynews.newsaggregator.repository.SubscribeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepo subscribeRepo;


    public List<Subscribe> getAllSubscribes() {
        return subscribeRepo.findAll();
    }

    public Subscribe getSubscribesById(Long id) {
        return subscribeRepo.findById(id).orElse(null); // Example ID, replace with actual logic
    }

    public Subscribe addSubscribe(Subscribe subscribe) {
        return subscribeRepo.save(subscribe); // Assuming a method to save subscribes exists
    }

    public Subscribe updateSubscribe(Long id, Subscribe subscribe) throws SubscribeNotFoundException {
        Optional<Subscribe> existingSubscribe = subscribeRepo.findById(id);
        if(!existingSubscribe.isPresent()) {
            throw new SubscribeNotFoundException("Subscribe with ID " + id + " not found");
        }
        Subscribe updatedSubscribe = existingSubscribe.get();
        updatedSubscribe.setTimestamp(subscribe.getTimestamp());
        updatedSubscribe.setSubscribedGenres(subscribe.getSubscribedGenres());
        updatedSubscribe.setNewsId(subscribe.getNewsId());
        updatedSubscribe.setUser(subscribe.getUser());

        return subscribeRepo.save(updatedSubscribe);
    }

    public ResponseEntity<String> deleteSubscribe(Long id) throws SubscribeNotFoundException {
        if (!subscribeRepo.existsById(id)) {
            throw new SubscribeNotFoundException("Subscribe with ID " + id + " not found");
        }
        subscribeRepo.deleteById(id);
        return ResponseEntity
                .status(200)
                .body("Subscribe deleted successfully");
    }
}
