package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
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

    private Date CreatedDate;

    public static UserResponse build(User user){
//        ur.setUsername(user.getUsername());
//        ur.setEmail(user.getEmail());
//        ur.setHpNumber(user.getHpNumber());
//        ur.setIsAdmin(user.getIsAdmin());
//        ur.setCreatedDate(user.getCreatedDate());
        return Optional.ofNullable(user).map(e -> {
            UserResponse userResponse = UserResponse.builder().build();
            BeanUtils.copyProperties(user, userResponse);

            return userResponse;
        }).orElse(null);
    }

}
