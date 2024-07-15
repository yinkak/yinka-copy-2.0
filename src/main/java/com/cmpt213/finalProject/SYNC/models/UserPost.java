package com.cmpt213.finalProject.SYNC.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_posts")
public class UserPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String url;

    private String caption;
    private LocalDateTime publishTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private Set<Integer> likes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_dislikes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "user_id")
    private Set<Integer> dislikes = new HashSet<>();

    // Default constructor
    protected UserPost() {}

    // Constructor
    public UserPost(String url, String caption, Integer userId) {
        this.url = url;
        this.caption = caption;
        this.publishTime = LocalDateTime.now();
        this.userId = userId;
    }

    // Getters, setters, functions
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public Set<Integer> getLikes() {
        return likes;
    }

    public void setLikes(Set<Integer> likes) {
        this.likes = likes;
    }

    public Set<Integer> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<Integer> dislikes) {
        this.dislikes = dislikes;
    }

    public void addLike(Integer userId) {
        this.likes.add(userId);
        this.dislikes.remove(userId);
    }

    public void removeLike(Integer userId) {
        this.likes.remove(userId);
    }

    public void addDislike(Integer userId) {
        this.dislikes.add(userId);
        this.likes.remove(userId);
    }

    public void removeDislike(Integer userId) {
        this.dislikes.remove(userId);
    }

    public boolean isLikedByUser(Integer userId) {
        return likes.contains(userId);
    }

    public boolean isDislikedByUser(Integer userId) {
        return dislikes.contains(userId);
    }

    public String getRelativePublishTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        long minutes = publishTime.until(currentTime, ChronoUnit.MINUTES);

        if (minutes < 1) {
            return "just now";
        } else if (minutes < 60) {
            return minutes + " minutes ago";
        } else {
            long hours = publishTime.until(currentTime, ChronoUnit.HOURS);
            if (hours < 24) {
                return hours + " hours ago";
            } else {
                long days = publishTime.until(currentTime, ChronoUnit.DAYS);
                return days + " days ago";
            }
        }
    }

    @Override
    public String toString() {
        return "UserPost [URL=" + url + ", caption=" + caption + ", publishTime=" + publishTime + "]";
    }
}
