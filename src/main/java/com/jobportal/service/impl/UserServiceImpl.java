package com.jobportal.service.impl;

import org.springframework.stereotype.Service;

import com.jobportal.dto.UserDTO;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }
    
}
