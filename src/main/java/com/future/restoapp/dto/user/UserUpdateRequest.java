package com.future.restoapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest implements Serializable {

    @Email(message = "email format invalid")
    private String email;

    private String password;

    private String hpNumber;

    public User toUser(){
        return Optional.of(this).map(dto -> {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            return user;
        }).orElse(null);
    }

}
