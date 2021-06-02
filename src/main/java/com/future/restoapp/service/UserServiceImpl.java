package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.UserRepository;
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

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
    public User create(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User update(@NotNull User user) throws Exception {
        return null;
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

}
