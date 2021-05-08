package com.future.restoapp.unitTest.service;

import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.UserRepository;
import com.future.restoapp.service.UserService;
import com.future.restoapp.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;

    private List<User> userList;

    @BeforeAll
    public static void setupMock(){
        // Define needed first time setup here
    }

    @BeforeEach
    public void setup(){
        userList = new ArrayList<>();

        user1 = User.builder()
                .username("hello")
                .email("hello@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

        user2 = User.builder()
                .username("admin")
                .email("admin@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(true)
                .build();

        userList.add(user1);
        userList.add(user2);
    }

    @AfterEach
    public void teardown(){
        user1 = user2 = null;
        userList = null;
    }

    @Test
    public void successfulRegister() throws Exception {
        User user = User.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

        when(userRepository.save(any())).thenReturn(null);

        userService.create(user);
    }

    @Test
    public void failRegisterAlreadyExist() throws Exception {
        when(userRepository.save(any())).thenReturn(null);
        when(userRepository.save(user1)).thenThrow(new RuntimeException());

        userService.create(user2);

        try {
            userService.create(user1);
        } catch (Exception ex) {
            return;
        }

        assert false;
    }

}
