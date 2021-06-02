package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface UserControllerPath{

    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/users";
    String BASE_PUBLIC = UrlBasePath.CURRENT_PUBLIC + "/users";
    String BASE_USER = UrlBasePath.CURRENT_USER + "/users";

    // Admin access
    String REGISTER_ADMIN = BASE_ADMIN;
    String FETCH_ONE = BASE_ADMIN + "/{username}";
    String FETCH = BASE_ADMIN;

    // Public access
    String REGISTER = BASE_PUBLIC;

    // User access
    String FETCH_ME = BASE_USER + "/me";
    String UPDATE = BASE_USER;
    String DELETE = BASE_USER;

}
