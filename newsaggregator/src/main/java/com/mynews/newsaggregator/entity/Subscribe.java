package com.mynews.newsaggregator.entity;

import com.mynews.newsaggregator.Genre;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subscribe {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "subscribe")
    private User user;
    private String newsId;
    private long timestamp;

    @ElementCollection
    private List<Genre> subscribedGenres;

    public Subscribe(Long id, String newsId, long timestamp) {
        this.id = id;
        this.newsId = newsId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Genre> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(List<Genre> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
