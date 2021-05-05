package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//import com.future.restoapp.model.entity.*;

@Table(name = User.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor  
@AllArgsConstructor
public class User extends BaseEntity {
    public static final String TABLE_NAME = "users";

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_HP_NUMBER = "hp_number";
    public static final String COLUMN_IS_ADMIN = "is_admin";

    @Column(name = User.COLUMN_USERNAME, unique = true, nullable = false)
    private String username;

    @Column(name = User.COLUMN_EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = User.COLUMN_PASSWORD)
    private String password;

    @Column(name = User.COLUMN_HP_NUMBER, nullable = false)
    private String hpNumber;

    @Column(name = User.COLUMN_IS_ADMIN)
    private Boolean isAdmin = false;

}
