package com.future.restoapp.controller;

import com.future.restoapp.model.dto.RegisterRequest;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = UserControllerPath.REGISTER, method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody RegisterRequest request) throws Exception{

        User user = request.convertToUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = UserControllerPath.FETCH_ONE, method = RequestMethod.GET)
    public ResponseEntity fetchOne(@PathVariable String username) throws Exception{
        User user = this.userService.findByUsername(username);

        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(user);
    }

}
