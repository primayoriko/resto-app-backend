package com.future.restoapp.controller;

import com.future.restoapp.constant.UrlBasePath;

public interface UserControllerPath {

    String BASE = UrlBasePath.CURRENT_CLIENT + "/users";
    String REGISTER_CLIENT = UrlBasePath.CURRENT_CLIENT + "/register";
    String REGISTER_ADMIN = UrlBasePath.CURRENT_ADMIN + "/register";
    String UPDATE = BASE;
    String FETCH_ONE = BASE + "/{username}";
    String FETCH_QUERY = BASE;
//    String DELETE_BY_CODE = "/{code}";
//    String UPDATE_NAME_BY_CODE = "/{code}/name";

}
