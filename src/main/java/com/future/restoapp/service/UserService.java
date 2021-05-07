package com.future.restoapp.service;

import com.future.restoapp.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	void create(User user) throws Exception;

	void updateByUsername(String username, User user) throws Exception;

	User findByUsername(String username) throws Exception;

	Page<User> find(Pageable pageable) throws Exception;

	void deleteByUsername(String username) throws Exception;

}
