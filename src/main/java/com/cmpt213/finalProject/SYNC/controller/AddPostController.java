package com.cmpt213.finalProject.SYNC.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cmpt213.finalProject.SYNC.models.UserModel;
import com.cmpt213.finalProject.SYNC.models.UserPostKey;
import com.cmpt213.finalProject.SYNC.service.PostService;
import com.cmpt213.finalProject.SYNC.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AddPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersService userService;

    

    @GetMapping("/addpost")
    public String showAddPostPage(Model model, HttpSession session) {
        UserModel sessionUser = (UserModel) session.getAttribute("session_user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", sessionUser);
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam("caption") String caption,
                          @RequestParam("image") MultipartFile image,
                          HttpSession session, Model model) throws IOException {
        UserModel sessionUser = (UserModel) session.getAttribute("session_user");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        UserPostKey post = postService.addPost(sessionUser.getId(), caption, image);
        model.addAttribute("post", post);
        return "redirect:/seeProfile"; // Redirect to user's profile or posts page
    }

     @GetMapping("/api/friendPosts")
    @ResponseBody
    public List<UserPostKey> getFriendPosts(HttpSession session) {
        UserModel sessionUser = (UserModel) session.getAttribute("session_user");
        if (sessionUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return postService.getRecentFriendPosts(sessionUser.getId());
    }
}
