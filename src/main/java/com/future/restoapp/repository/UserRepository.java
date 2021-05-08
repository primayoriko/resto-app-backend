package com.future.restoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    void deleteByUsername(String username);

}
