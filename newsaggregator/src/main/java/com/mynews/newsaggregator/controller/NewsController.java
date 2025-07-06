package com.mynews.newsaggregator.controller;

import com.mynews.newsaggregator.entity.News;
import com.mynews.newsaggregator.exception.NewsNotFoundException;
import com.mynews.newsaggregator.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService; // Assuming a service layer exists for newss

    @GetMapping("/newss")
    public List<News> getAllNewss() {
        return newsService.getAllNewss();
    }

    @GetMapping("/newss/{id}")
    public News getNewssById(@RequestParam Long id) {
        return newsService.getNewssById(id);
    }

    @PostMapping("/newss")
    public News addNews(@RequestBody News news) {
        return newsService.addNews(news); // Assuming a method to add newss exists
    }

    @PutMapping("/newss/{id}")
    public News updateNews(@RequestParam Long id, @RequestBody News news) throws NewsNotFoundException {
        return newsService.updateNews(id, news); // Reusing addNews for update
    }

    @DeleteMapping("/newss/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Long id) throws NewsNotFoundException {
        return newsService.deleteNews(id); // Assuming a method to delete newss exists
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity handleNewsNotFound(NewsNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body("News not found: " + ex.getMessage());
    }
}
