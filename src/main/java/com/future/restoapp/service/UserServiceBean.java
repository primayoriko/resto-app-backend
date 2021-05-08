package com.future.restoapp.service;

import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceBean.class);

	@Autowired
	private UserRepository userRepository;

	@Override
    public void create(User user) throws Exception{
        this.userRepository.save(user);
    }

    @Override
    public void updateByUsername(String username, User user) throws Exception {
        User oldUser = this.userRepository.findByUsername(username);
        if(oldUser != null){
            // TODO: Checking field that gonna updated in user and update it to oldUser

            this.userRepository.save(oldUser);
        }
    }

    @Override
    public User findByUsername(String username) throws Exception{
	    return this.userRepository.findByUsername(username);
    }

    @Override
    public Page<User> find(Pageable pageable) throws Exception{
	    return null;
    }

    @Override
    public void deleteByUsername(String username) throws Exception{
        this.userRepository.deleteByUsername(username);
    }

}
