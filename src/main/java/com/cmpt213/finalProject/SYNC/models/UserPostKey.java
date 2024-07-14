package com.cmpt213.finalProject.SYNC.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserPostKey implements Serializable {
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "post_id")
    private String URL;

    private String caption;
    private int likeCount;
    private int dislikeCount;
    private LocalDateTime publishTime;

    // Default constructor
    protected UserPostKey() {}

    // Constructor
    public UserPostKey(String URL, String caption, Integer userId) {
        this.URL = URL;
        this.caption = caption;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.publishTime = LocalDateTime.now();
        this.userId = userId;
    }

    // Getters, setters, functions
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
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
        return "UserPost [URL=" + URL + ", caption=" + caption + ", likeCount=" + likeCount + ", dislikeCount="
                + dislikeCount + ", publishTime=" + publishTime + "]";
    }
}
