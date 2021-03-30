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

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

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
