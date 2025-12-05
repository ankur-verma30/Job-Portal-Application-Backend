package com.jobportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utility.Utilities;

import lombok.RequiredArgsConstructor;

@Service(value="userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        userDTO.setId(Utilities.getNextSequence("user"));
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();     
    }
    
}
