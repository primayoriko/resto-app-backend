package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface MenuControllerPath{

    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/menus";
    String BASE_PUBLIC = UrlBasePath.CURRENT_PUBLIC + "/menus";

    // Admin access
    String CREATE = BASE_ADMIN;
    String UPDATE = BASE_ADMIN;
    String DELETE = BASE_ADMIN + "/{id}";

    // Public access
    String FETCH = BASE_PUBLIC;
    String FETCH_ONE = BASE_PUBLIC + "/{id}";

}
