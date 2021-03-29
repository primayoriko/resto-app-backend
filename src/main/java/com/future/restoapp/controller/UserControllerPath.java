package com.future.restoapp.controller;

import com.future.restoapp.constant.UrlBasePath;

public interface UserControllerPath {

    String USER = UrlBasePath.CLIENT_CURRENT_URL + "/users";
    String REGISTER = USER + "/register";
    String UPDATE = USER;
//    String DELETE_BY_CODE = "/{code}";
//    String UPDATE_NAME_BY_CODE = "/{code}/name";

}
