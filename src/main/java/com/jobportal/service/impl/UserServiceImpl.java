package com.jobportal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.LoginDTO;
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
        Optional<User> optionalUser=userRepository.findByEmail(userDTO.getEmail());
        if(optionalUser.isPresent()) throw new JobPortalException("USER_FOUND");
        userDTO.setId(Utilities.getNextSequence("user"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user=userDTO.toEntity();
        user=userRepository.save(user);
        return user.toDTO();     
    }


    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user=userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new JobPortalException("INVALID_PASSWORD");
        return user.toDTO();
    }
    
}
