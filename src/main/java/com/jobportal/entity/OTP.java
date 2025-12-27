package com.jobportal.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "otp")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OTP {

    @Id
    @Email(message = "{user.email.invalid}")
    @NotBlank(message ="{user.email.absent}")
    private String email;

    private String otpCode;

    private LocalDateTime creationTime;
}
