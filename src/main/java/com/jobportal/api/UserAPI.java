package com.jobportal.api;

import com.jobportal.dto.ResponseDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jobportal.dto.LoginDTO;
import com.jobportal.dto.UserDTO;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("*")
@Validated
public class UserAPI{

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) throws JobPortalException {
        userDTO = userService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
        }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
        return new ResponseEntity<>(userService.loginUser(loginDTO),HttpStatus.OK);
        }

    @PostMapping("/send-otp/{email}")
    public ResponseEntity<ResponseDTO> sendOTP(@PathVariable @Email(message = "{user.email.invalid}") String email) throws Exception{
        userService.sendOTP(email);
        return new ResponseEntity<>(new ResponseDTO("OPT Sent Successfully"),HttpStatus.OK);
    }

    @GetMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<ResponseDTO> verifyOTP(@PathVariable @Email(message = "{user.email.invalid}") String email,
                                                 @PathVariable @Pattern(regexp = "^[0-9]{6}$",
                                                         message="{otp.invalid}") String otp) throws Exception{
        userService.verifyOTP(email,otp);
        return new ResponseEntity<>(new ResponseDTO("OPT Verified!"),HttpStatus.ACCEPTED);
    }

}