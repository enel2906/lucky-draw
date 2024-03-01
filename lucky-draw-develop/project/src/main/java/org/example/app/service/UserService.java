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
}
