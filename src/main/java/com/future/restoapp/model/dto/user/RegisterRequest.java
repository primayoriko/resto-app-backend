package com.future.restoapp.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
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
    @Email(message = "email format invalid")
    private String email;

    @NotBlank(message = "password must be specified")
    private String password;

    @NotBlank(message = "hpNumber must be specified")
    private String hpNumber;

    public User toUser(){
        return Optional.of(this).map(dto -> {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            return user;
        }).orElse(null);
    }

}
