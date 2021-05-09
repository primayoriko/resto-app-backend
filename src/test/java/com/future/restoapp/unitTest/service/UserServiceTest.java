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
    private User user3;

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

        user3 = User.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
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
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);

            if(userList.contains(user)){
                throw new RuntimeException();
            } else {
                userList.add(user);
            }

            return null;
        }).when(userRepository).save(any());

        userService.create(user3);
        verify(userRepository, times(1)).save(user3);
    }

    @DisplayName("Fail to create an existing data")
    @Test
    public void failRegisterAlreadyExist() throws Exception {
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);

            if(userList.contains(user)){
                throw new RuntimeException();
            } else {
                userList.add(user);
            }

            return null;
        }).when(userRepository).save(any());

        try {
            userService.create(user3);
            userService.create(user3);
            verify(userRepository, times(2)).save(user3);
        } catch (Exception ex) {
            return;
        }
        assert false;
    }

}
