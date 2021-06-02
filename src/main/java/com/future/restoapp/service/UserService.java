package com.future.restoapp.service;

import com.future.restoapp.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

public interface UserService {

	void create(User user) throws Exception;

	void deleteByUsername(@NotBlank String username) throws Exception;

	void updateByUsername(@NotBlank String username, User user) throws Exception;

	User findByUsername(@NotBlank String username) throws Exception;

	Page<User> findByQuery(String username, String Email, String hpNumber, Pageable pageable) throws Exception;

}
