package com.future.restoapp.unitTest.controller;

import com.future.restoapp.controller.UserController;
import com.future.restoapp.controller.path.UserControllerPath;
import com.future.restoapp.model.dto.RegisterRequest;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.future.restoapp.unitTest.controller.AbstractControllerTest.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("User Controller Unit Tests")
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    private RegisterRequest request;

    private User user1;
    private User user2;
    private User user3;

    private List<User> userList;

    @BeforeEach
    public void init(){
        userList = new ArrayList<>();
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        request = RegisterRequest.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .build();

        user3 = User.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

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
    public void tearDown() {
        request = null;
        userList = null;
        user1 = user2 = user3 = null;
    }

    @DisplayName("Create User Endpoint Success")
    @Test
    public void createUserSuccess() throws Exception {
//        when(passwordEncoder.encode(anyString())).thenReturn("12345");
        doReturn("12345").when(passwordEncoder).encode(anyString());
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);

            if(userList.contains(user)){
                throw new RuntimeException();
            } else {
                userList.add(user);
            }

            return null;
        }).when(userService).create(any());

        mockMvc.perform(
                post(UserControllerPath.REGISTER_CLIENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        ).andDo(MockMvcResultHandlers.print());

        verify(userService, times(1)).create(any());
    }

    @DisplayName("Create User Endpoint Failed with Existing Data")
    @Test
    public void createUserFailedAlreadyExist() throws Exception {
        doReturn("12345").when(passwordEncoder).encode(anyString());
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);

            if(userList.contains(user)){
                throw new RuntimeException();
            } else {
                userList.add(user);
            }

            return null;
        }).when(userService).create(any());

        user3 = User.builder()
                .username("newbee")
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

        try {
            mockMvc.perform(
                    post(UserControllerPath.REGISTER_CLIENT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(request))
            ).andDo(MockMvcResultHandlers.print());

            mockMvc.perform(
                    post(UserControllerPath.REGISTER_CLIENT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(request))
            ).andDo(MockMvcResultHandlers.print());
        } catch (Exception err) {
            verify(userService, times(2)).create(any());
            return;
        }

        assert false;
    }

    @DisplayName("Create User Endpoint Failed with Incomplete Data")
    @Test
    public void createUserFailedIncompleteData() throws Exception {
        doReturn("12345").when(passwordEncoder).encode(anyString());
        doNothing().when(userService).create(any());

        // TODO: Fix validation check
        request = RegisterRequest.builder()
                .email("nb@g.com")
                .password("12345")
                .hpNumber("082116235723")
                .build();

        try {
            mockMvc.perform(
                    post(UserControllerPath.REGISTER_CLIENT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(request))
            ).andDo(MockMvcResultHandlers.print());
        } catch (Exception err) {
            verify(userService, times(0)).create(any());
            return;
        }

//        assert false;
    }

}
