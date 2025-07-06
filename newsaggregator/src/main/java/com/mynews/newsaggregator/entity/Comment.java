package com.mynews.newsaggregator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String newsId;
    private String userId;
    private String content;
    private long timestamp;

    @OneToMany
    private List<Comment> replies;

    @OneToMany(mappedBy = "comment")
    private List<Likes> likes;

    public Comment(Long id, String newsId, String userId, String content, long timestamp) {
        this.id = id;
        this.newsId = newsId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
