package com.jobportal.dto;

import com.jobportal.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    @NotBlank(message="{user.name.absent}")
    private String name;
    @Email(message="{user.email.absent}")
    @NotBlank(message="Email is blank")
    private String email;
    @NotBlank(message="Password is blank")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&])(?=\\S+$).{8,15}",message="Password is not valid")
    private String password;
    @NotNull
    private AccountType accountType;

      public User toEntity(){
        return new User(this.id,this.name,this.email,this.password,this.accountType);
    }
    
}
