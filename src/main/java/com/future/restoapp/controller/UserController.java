package com.future.restoapp.controller;

import com.future.restoapp.model.dto.RegisterRequest;
import com.future.restoapp.model.dto.UserResponse;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "User")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = UserControllerPath.REGISTER_CLIENT, method = RequestMethod.POST)
    public ResponseEntity registerClient(@Valid @RequestBody RegisterRequest request) throws Exception{

        User user = request.convertToUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = UserControllerPath.REGISTER_ADMIN, method = RequestMethod.POST)
    public ResponseEntity registerAdmin(@Valid @RequestBody RegisterRequest request) throws Exception{

        User user = request.convertToUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setIsAdmin(true);

        this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = UserControllerPath.FETCH_ONE, method = RequestMethod.GET)
    public ResponseEntity fetchOne(@PathVariable String username) throws Exception{
        User user = this.userService.findByUsername(username);

        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(UserResponse.buildFromUser(user));
    }

}
