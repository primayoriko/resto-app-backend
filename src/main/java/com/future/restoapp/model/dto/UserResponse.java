package com.future.restoapp.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.future.restoapp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static UserResponse buildFromUser(User user){
        UserResponse ur = new UserResponse();

        ur.setUsername(user.getUsername());
        ur.setEmail(user.getEmail());
        ur.setHpNumber(user.getHpNumber());
        ur.setIsAdmin(user.getIsAdmin());
        ur.setCreatedDate(user.getCreatedDate());

        return ur;
    }
}
