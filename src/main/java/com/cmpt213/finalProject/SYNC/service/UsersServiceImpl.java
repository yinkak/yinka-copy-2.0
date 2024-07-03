package com.cmpt213.finalProject.SYNC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpt213.finalProject.SYNC.models.UserModel;
import com.cmpt213.finalProject.SYNC.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel registerUser(String login, String password, String email, String name,  String gender, String dob, String location, String phoneNumber) {
        if (login == null || password == null) {
            System.out.println("Registration failed: login or password is null");
            return null;
        } else {
            if (userRepository.findByLogin(login).isPresent()) {
                System.out.println("Duplicate User");
                return null;
            }

            UserModel user = new UserModel();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setGender(gender);
            user.setDob(dob);
            user.setLocation(location);
            user.setPhoneNumber(phoneNumber);

            return userRepository.save(user);
        }
    }

    @Override
    public UserModel authentication(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deactivateUser(Integer id) {
        UserModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public void activateUser(Integer id){
        UserModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
        }
    }
    
    // Method to update user information
    public UserModel updateUser(String login, String dob, String gender, String phoneNumber, String location) {
        Optional<UserModel> optionalUser = userRepository.findByLogin(login);

        System.out.println(login);
    
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            // Update the user fields
            user.setDob(dob);
            user.setGender(gender);
            user.setPhoneNumber(phoneNumber);
            user.setLocation(location);
            // Save the updated user back to the repository
            userRepository.save(user);
            return user;
        }
        return null; // Handle case where user is not found
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }



}
