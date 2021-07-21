package com.future.restoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);

}
