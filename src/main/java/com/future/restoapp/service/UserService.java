package com.future.restoapp.service;

import com.future.restoapp.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface UserService {

	User create(@NotNull User user) throws Exception;

	User findByUsername(@NotBlank String username) throws Exception;

	Page<User> findByQuery(String username, String email, String hpNumber, Pageable pageable) throws Exception;

	void deleteById(long id) throws Exception;

	User update(@NotNull User user) throws Exception;

}
