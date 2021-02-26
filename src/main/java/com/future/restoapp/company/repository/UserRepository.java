package com.future.restoapp.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.company.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
