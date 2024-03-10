package com.lenam.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getOneUserByEmail(String email) {
        return this.userRepository.findFirstUserByEmail(email);
    }

    public User getOneUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User handleSaveUser(User user) {
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
