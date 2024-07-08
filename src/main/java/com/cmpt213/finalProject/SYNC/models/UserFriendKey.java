package com.cmpt213.finalProject.SYNC.models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class UserFriendKey implements Serializable {
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "friend_id")
    Integer friendId;

    public UserFriendKey() {}

    public UserFriendKey(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    // Getters, setters, hashCode, and equals
    @Override
    public int hashCode() {
        return Objects.hash(userId, friendId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserFriendKey that = (UserFriendKey) obj;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(friendId, that.friendId);
    }
}
