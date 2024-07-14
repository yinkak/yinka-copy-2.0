package com.cmpt213.finalProject.SYNC.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cmpt213.finalProject.SYNC.models.UserModel;
import com.cmpt213.finalProject.SYNC.models.UserPostKey;
import com.cmpt213.finalProject.SYNC.repository.UserRepository;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImgurService imgurService;

    public UserPostKey addPost(Integer userId, String caption, MultipartFile image) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String imageUrl = imgurService.uploadImage(image);
        UserPostKey post = new UserPostKey(imageUrl, caption, user.getId());
        user.getUserPosts().add(post);
        userRepository.save(user);

        return post;
    }

    public List<UserPostKey> getUserPosts(Integer userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserPosts();
    }

    public List<UserPostKey> getRecentFriendPosts(Integer userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Integer> friendIds = user.getFriends().stream().map(f -> f.getFriendId()).collect(Collectors.toList());

        return friendIds.stream()
                .map(id -> userRepository.findById(id).orElse(null))
                .filter(friend -> friend != null)
                .flatMap(friend -> friend.getUserPosts().stream())
                .filter(post -> post.getPublishTime().isAfter(LocalDateTime.now().minusWeeks(1)))
                .sorted((p1, p2) -> p2.getPublishTime().compareTo(p1.getPublishTime()))
                .collect(Collectors.toList());
    }
}
