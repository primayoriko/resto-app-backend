package com.future.restoapp.service.impl;

import com.future.restoapp.domain.User;
import com.future.restoapp.repository.UserRepository;
import com.future.restoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
    public User create(@NotNull User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(@NotBlank String username) throws Exception {
	    return this.userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findByQuery(String username, String email,
                                  String hpNumber, Pageable pageable) throws Exception {
        User user = User
                .builder()
                .username(username)
                .email(email)
                .hpNumber(hpNumber)
                .build();

        user.setCreatedDate(null);
        user.setUpdatedDate(null);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("hpNumber", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return userRepository.findAll(Example.of(user, exampleMatcher), pageable);
    }

    @Override
    public void deleteById(long id) throws Exception {
        userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with specified ID not found"));
        userRepository.deleteById(id);
    }


    @Override
    public User update(@NotNull User user) throws Exception {
        User userDb = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new NoSuchElementException("User with specified ID not found"));
        userDb.update(user);
        return userRepository.save(userDb);
    }

}
