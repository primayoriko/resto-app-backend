package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.future.restoapp.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.company.entity.*;

@Table(name = com.future.restoapp.company.entity.User.TABLE_NAME)
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

    @Column(name = User.COLUMN_USERNAME)
    private String username;

    @Column(name = User.COLUMN_EMAIL)
    private String email;

    @Column(name = User.COLUMN_PASSWORD)
    private String password;

    @Column(name = User.COLUMN_HP_NUMBER)
    private String hpNumber;

    @Column(name = User.COLUMN_IS_ADMIN)
    private Boolean isAdmin;
}
