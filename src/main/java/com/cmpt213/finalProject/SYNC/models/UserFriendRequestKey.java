package com.cmpt213.finalProject.SYNC.models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class UserFriendRequestKey implements Serializable {
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "friend_request_id")
    Integer friendRequestId;

    public UserFriendRequestKey() {}

    public UserFriendRequestKey(Integer userId, Integer friendRequestId) {
        this.userId = userId;
        this.friendRequestId = friendRequestId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFriendRequestId() {
        return friendRequestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, friendRequestId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserFriendRequestKey that = (UserFriendRequestKey) obj;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(friendRequestId, that.friendRequestId);
    }
}
