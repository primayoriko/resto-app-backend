package com.future.restoapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private String username;

    private String email;

    private String hpNumber;

    private Boolean isAdmin;

    private LocalDateTime createdDate;

    public static UserResponse build(User user){
        return Optional.ofNullable(user).map(entity -> {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(entity, userResponse);
            return userResponse;
        }).orElse(null);
    }

}
