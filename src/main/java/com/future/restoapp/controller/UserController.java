package com.future.restoapp.controller;

import com.future.restoapp.base.BaseResponse;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = UserControllerPath.BASE)
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = UserControllerPath.REGISTER,method = RequestMethod.POST)
    public BaseResponse register() throws Exception{
        User user = new User();

        this.userService.create(user);

        return new BaseResponse(null, null, true);
    }
}
