package com.mynews.newsaggregator.service;

import com.mynews.newsaggregator.entity.News;
import com.mynews.newsaggregator.exception.NewsNotFoundException;
import com.mynews.newsaggregator.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private RestTemplate _restTemplate;

    public List<News> getAllNewss() {
        return newsRepo.findAll();
    }

    public News getExternalNews(){
        News news = _restTemplate.getForObject("https://gnews.io/api/v4/search?q=example&apikey=" + "84e597f88b0125b5dab20666e903e54a", News.class);
        return news; // Assuming ApiResult has a method getArticles() that returns a list of News
    }

    public News getNewssById(Long id) {
        return newsRepo.findById(id).orElse(null); // Example ID, replace with actual logic
    }

    public News addNews(News news) {
        return newsRepo.save(news); // Assuming a method to save newss exists
    }

    public News updateNews(Long id, News news) throws NewsNotFoundException {
        Optional<News> existingNews = newsRepo.findById(id);
        if(!existingNews.isPresent()) {
            throw new NewsNotFoundException("News with ID " + id + " not found");
        }
        News updatedNews = existingNews.get();
        updatedNews.setPublishedAt(news.getPublishedAt());
        updatedNews.setTitle(news.getTitle());
        updatedNews.setContent(news.getContent());
        updatedNews.setDescription(news.getDescription());
        updatedNews.setGenre(news.getGenre());

        return newsRepo.save(updatedNews);
    }

    public ResponseEntity<String> deleteNews(Long id) throws NewsNotFoundException {
        if (!newsRepo.existsById(id)) {
            throw new NewsNotFoundException("News with ID " + id + " not found");
        }
        newsRepo.deleteById(id);
        return ResponseEntity
                .status(200)
                .body("News deleted successfully");
    }
}
