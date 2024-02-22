package org.example.app.service;

import org.example.app.repository.UserRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String addUser(String name) throws Exception {
        return userRepository.addUser(name);
    }
    public boolean isNullUser(String userId) throws Exception {
        return userRepository.isNullUser(userId);
    }
    public void executeRoll(String userId) throws Exception {
        userRepository.executeRoll(userId);
    }
    public boolean isValidNumRoll(String userId) throws Exception {
        return userRepository.isValidNumRoll(userId);
    }
    public void acceptRoll(String userId) throws Exception {
        userRepository.acceptRoll(userId);
    }
    public int getNumRoll(String userId) throws Exception {
        return userRepository.getNumRoll(userId);
    }
}
