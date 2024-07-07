package com.cmpt213.finalProject.SYNC.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UserPostKey implements Serializable{
    @Column(name = "user_id")
    Integer userId;
    
    @Column(name = "post_id")
    private String URL;
    
    private String caption;
    private int likeCount;
    private int dislikeCount;
    private LocalDateTime publishTime;


    //default constructor
    protected UserPostKey(){}

    //Constructor
    public UserPostKey(String URL, String caption, User user)
    {
        this.URL = URL;
        this.caption = caption;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.publishTime = LocalDateTime.now();
        this.user = user;
    }


    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    */

    //getters, setters, functions
    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                + dislikeCount + ", publishTime=" + publishTime + ", user=" + user + "]";
    }

}
