package com.future.restoapp.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.future.restoapp.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "username must be specified")
    private String username;

    @NotBlank(message = "email must be specified")
    private String email;

    @NotBlank(message = "password must be specified")
    private String password;

    @NotBlank(message = "hpNumber must be specified")
    private String hpNumber;

    public User convertToUser(){
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setHpNumber(hpNumber);
        user.setUsername(username);

        return user;
    }

}
