package com.cmpt213.finalProject.SYNC.service;

import com.cmpt213.finalProject.SYNC.models.UserModel;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    UserModel registerUser(String login, String password, String email, String name ,String gender, String dob, String location, Integer phoneNumber, String pictureUpload);
    UserModel authentication(String login, String password);
    List<UserModel> getAllUsers();
    void deactivateUser(Integer id);
    void activateUser(Integer id);
    UserModel updateUser(String login, String dob, String gender, int phoneNumber, String pictureUpload);
   
}
