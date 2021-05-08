package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

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

    public User toUser(){
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setHpNumber(hpNumber);
//        user.setUsername(username);
        return Optional.of(this).map(e -> {
            User user = User.builder().build();
            BeanUtils.copyProperties(e, user);

            return user;
        }).orElse(null);
    }

}
