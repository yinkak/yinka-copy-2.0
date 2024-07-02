package com.cmpt213.finalProject.SYNC.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cmpt213.finalProject.SYNC.models.UserModel;

import com.cmpt213.finalProject.SYNC.service.UsersService;

@Controller
public class UsersController {
    @Autowired
    private UsersService userService;
    

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("register request: " + userModel);
       
    
        // Hash the password using your custom hash function
        String hashedPassword = UserModel.hashFunc(userModel.getPassword());

        // Set the hashed password in the userModel
        userModel.setPassword(hashedPassword);

        // Use the hashed password instead of the plain password
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),
                userModel.getEmail(), userModel.getName());

        if (registeredUser == null) {
            System.out.println("Registration failed: duplicate user or invalid data");
            return "error_page";
        }

        model.addAttribute("userLogin", userModel.getLogin());
        return "personalAccount";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request" + userModel);
        String hashedPassword = UserModel.hashFunc(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        UserModel authenticate = userService.authentication(userModel.getLogin(), userModel.getPassword());

        if (authenticate != null) {
            if (authenticate.isActive()) {
                model.addAttribute("userLogin", userModel.getLogin());
                return "personalAccount";
            } else {
                model.addAttribute("error", "You have been deactivated. Please contact admin!");
                return "login_page";
            }
        } else {
            model.addAttribute("error", "Invalid login credentials");
            return "login_page";
        }
    }

    @GetMapping("/adminlogin")
    public String getAdminLoginPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "admin_login_page";
    }

    @PostMapping("/adminlogin")
    public String adminLogin(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("admin login request: " + userModel);

        String hashedPassword = UserModel.hashFunc(userModel.getPassword());
        userModel.setPassword(hashedPassword);

        UserModel authenticate = userService.authentication(userModel.getLogin(), userModel.getPassword());

        if (authenticate != null && authenticate.isAdmin()) {
            model.addAttribute("userLogin", userModel.getLogin());
            model.addAttribute("allUsers", userService.getAllUsers());
            return "admin_dashboard";
        } else {
            System.out.println("Admin login failed: invalid credentials or not an admin");
            return "error_page";
        }
    }

    @GetMapping("/admin/home")
    public String getAdminHomePage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin_dashboard";
    }

    @PostMapping("/admin/deactivate/{id}")
    public String deactivateUser(@PathVariable("id") Integer id) {
        userService.deactivateUser(id);
        return "redirect:/admin/home";
    }

    @PostMapping("/admin/activate/{id}")
    public String activateUser(@PathVariable("id") Integer id) {
        userService.activateUser(id);
        return "redirect:/admin/home";
    }
    

    @PostMapping("/intro")
public String submitIntroForm(@ModelAttribute UserModel userModel, Model model) {
    System.out.println("intro form submission: " + userModel);

    // Update user information once they have registered the new stuff
    userService.updateUserDetails(userModel.getLogin(), userModel.getGender(),
                                   userModel.getDob(), userModel.getLocation(),
                                   userModel.getPhoneNumber());

    // Redirect to a success page or any appropriate page after intro form submission
    return "success_page";
}

}
