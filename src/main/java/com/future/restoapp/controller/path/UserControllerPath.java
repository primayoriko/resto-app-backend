package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface UserControllerPath{

    String BASE_CLIENT = UrlBasePath.CURRENT_CLIENT + "/users";
    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/users";

    // Admin access
    String REGISTER_ADMIN = UrlBasePath.CURRENT_ADMIN + "/register";
    String DELETE_ADMIN = BASE_ADMIN;
    String FETCH_ONE = BASE_ADMIN + "/{username}";
    String FETCH_ALL = BASE_ADMIN;

    // Client access
    String UPDATE = BASE_CLIENT;
    String DELETE_CLIENT = BASE_CLIENT;
    String FETCH_OWN = BASE_CLIENT;

    // Public access
    String REGISTER = UrlBasePath.CURRENT_PUBLIC + "/register";

}
