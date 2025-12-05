package com.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @Email(message = "{user.email.invalid}")
  @NotBlank(message = "{user.email.absent}")
  private String email;
  @NotBlank(message = "{user.password.absent}")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&])(?=\\S+$).{8,15}", message = "{user.password.invalid}")
  private String password;
}
