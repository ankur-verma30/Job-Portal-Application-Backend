package com.jobportal.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.jobportal.dto.ResponseDTO;
import com.jobportal.entity.OTP;
import com.jobportal.repository.OTPRepository;
import com.jobportal.utility.OtpEmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender javaMailSender;

    private final OTPRepository otpRepository;


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

    @Override
    public boolean sendOTP(String email) throws Exception {
        //check whether the email is registered in our db or not
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Your OTP is here from JobHook");
        String generatedOTP=Utilities.generateOTP();

        OTP otp=new OTP(email,generatedOTP, LocalDateTime.now());
        otpRepository.save(otp);
        String htmlMessage= OtpEmailTemplate.getOtpTemplate(generatedOTP,user.getName());
        mimeMessageHelper.setText(htmlMessage,true);
        javaMailSender.send(mimeMessage);
        return true;


    }

    @Override
    public boolean verifyOTP(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findById(email).orElseThrow(() -> new JobPortalException("EMAIL_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp)) throw  new JobPortalException("INCORRECT_OTP");
        return true;
    }

}
