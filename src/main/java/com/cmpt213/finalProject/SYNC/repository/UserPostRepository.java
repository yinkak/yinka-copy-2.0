package com.cmpt213.finalProject.SYNC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpt213.finalProject.SYNC.models.UserPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Integer> {

    @Query("SELECT p FROM UserPost p WHERE p.userId = :userId")
    List<UserPost> findAllByUserId(@Param("userId") Integer userId);
}
