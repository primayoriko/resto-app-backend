package com.future.restoapp.controller;

import com.future.restoapp.base.BaseResponse;
import com.future.restoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = UserControllerPath.BASE)
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping()
//    public BaseResponse create(){
//
//    }
}
