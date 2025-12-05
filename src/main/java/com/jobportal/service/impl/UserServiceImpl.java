package com.jobportal.service.impl;

import org.springframework.stereotype.Service;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;

import lombok.RequiredArgsConstructor;

@Service(value="userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();     
    }
    
}
