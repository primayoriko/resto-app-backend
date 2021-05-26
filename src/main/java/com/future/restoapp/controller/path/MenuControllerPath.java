package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface MenuControllerPath {

    String BASE_CLIENT = UrlBasePath.CURRENT_CLIENT + "/menus";
    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/menus";

    // Admin access
    String CREATE = BASE_ADMIN;
    String DELETE = BASE_ADMIN + "/{id}";
    String UPDATE = BASE_ADMIN;

    String FETCH_ADMIN = BASE_ADMIN;
    String FETCH_ONE_ADMIN = BASE_ADMIN + "/{id}";

    // Client access
    String FETCH_CLIENT = BASE_CLIENT;
    String FETCH_ONE_CLIENT = BASE_CLIENT + "/{id}";

}
