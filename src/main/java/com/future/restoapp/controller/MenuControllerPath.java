package com.future.restoapp.controller;

import com.future.restoapp.constant.UrlBasePath;

public interface MenuControllerPath {

    String CLIENT_BASE = UrlBasePath.CURRENT_CLIENT + "/menus";
    String ADMIN_BASE = UrlBasePath.CURRENT_ADMIN + "/menus";

    String CREATE = ADMIN_BASE;

    String DELETE = ADMIN_BASE + "/{id}";

    String UPDATE = ADMIN_BASE;

    String FETCH_CLIENT = CLIENT_BASE;
    String FETCH_ADMIN = ADMIN_BASE;

    String FETCH_ONE_CLIENT = CLIENT_BASE + "/{id}";
    String FETCH_ONE_ADMIN = ADMIN_BASE + "/{id}";

}
