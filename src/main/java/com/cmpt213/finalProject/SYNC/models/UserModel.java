package com.cmpt213.finalProject.SYNC.models;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "users_table")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String login; //username
    String password;
    String email;
    String name;
    boolean isAdmin;
    boolean isActive = true;
    // String gender; 
    // String dob;
    // String location; 
    // String pictureUpload; 
    
    // int phoneNumber; 


    

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    // public String getGender() {
    //     return gender;
    // }

    // public void setGender(String gender) {
    //     this.gender = gender;
    // }

    // public String getDob() {
    //     return dob;
    // }

    // public void setDob(String dob) {
    //     this.dob = dob;
    // }

    // public String getLocation() {
    //     return location;
    // }

    // public void setLocation(String location) {
    //     this.location = location;
    // }

    // public String getPictureUpload() {
    //     return pictureUpload;
    // }

    // public void setPictureUpload(String pictureUpload) {
    //     this.pictureUpload = pictureUpload;
    // }
    
    // public int getPhoneNumber() {
    //     return phoneNumber;
    // }

    // public void setPhoneNumber(int phoneNumber) {
    //     this.phoneNumber = phoneNumber;
    // }

    public static String hashFunc(String password) {
        // Step 1: Mirror the password
        char[] chars = password.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[length - 1 - i];
            chars[length - 1 - i] = temp;
        }
        String mirroredPassword = new String(chars);
        
        // Step 2: Process each character of the mirrored password
        StringBuilder hashedPass = new StringBuilder();
        for (int i = 0; i < mirroredPassword.length(); i++) {
            char c = mirroredPassword.charAt(i);
            int asciiValue = (int) c;
            long twoPowerAscii = (long) Math.pow(2, asciiValue);
            // Step 3 & 4: Concatenate character, its ASCII value, and 2^ASCII value as strings
            hashedPass.append(c).append(asciiValue).append(twoPowerAscii);
        }
        return hashedPass.toString();
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", login=" + login + ", email=" + email + ", isAdmin=" + isAdmin + ", isActive=" + isActive + "]";
    }
}
