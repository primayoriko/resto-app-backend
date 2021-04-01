package com.future.restoapp.controller;

import com.future.restoapp.base.BaseResponse;
import com.future.restoapp.model.dto.RegisterRequest;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = UserControllerPath.REGISTER, method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterRequest request) throws Exception{
        System.out.println(request);

        User user = request.convertToUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println(user);

        this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
