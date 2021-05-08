package com.future.restoapp.unitTest.service;

import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.UserRepository;
import com.future.restoapp.service.UserService;
import com.future.restoapp.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("User Service Unit Tests")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;

    private List<User> userList;

    @BeforeAll
    public static void initAll(){
        // Define needed first time setup here
    }

    @BeforeEach
    public void init(){
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
    public void tearDown(){
        user1 = user2 = null;
        userList = null;
    }

    @DisplayName("Successfully create a new and complete user data")
    @Test
    public void successfulRegister() throws Exception {
        when(userRepository.save(any())).thenReturn(null);

        User user = User.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

        userService.create(user);
        verify(userRepository, times(1)).save(user);
    }

    @DisplayName("Fail to create an existing data")
    @Test
    public void failRegisterAlreadyExist() throws Exception {
        when(userRepository.save(any())).thenReturn(null);
        when(userRepository.save(user1)).thenThrow(new RuntimeException());

        userService.create(user2);
        verify(userRepository, times(1)).save(user2);

        try {
            userService.create(user1);
            verify(userRepository, times(1)).save(user1);
        } catch (Exception ex) {
            return;
        }

        assert false;
    }

}
