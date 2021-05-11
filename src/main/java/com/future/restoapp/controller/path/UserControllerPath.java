package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface UserControllerPath {

    String BASE = UrlBasePath.CURRENT_CLIENT + "/users";

    String REGISTER_CLIENT = UrlBasePath.CURRENT_CLIENT + "/register";
    String REGISTER_ADMIN = UrlBasePath.CURRENT_ADMIN + "/register";

    String DELETE = BASE;

    String UPDATE = BASE;

    String FETCH_ONE = BASE + "/{username}";

    String FETCH_QUERY = BASE;

}
