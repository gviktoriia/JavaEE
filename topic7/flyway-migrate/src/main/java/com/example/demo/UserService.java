package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElse(null);
    }

    @Transactional
    public void createUser(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
