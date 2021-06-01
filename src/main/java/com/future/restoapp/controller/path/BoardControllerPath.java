package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface BoardControllerPath {

    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/boards";
    String BASE_PUBLIC = UrlBasePath.CURRENT_PUBLIC + "/boards";

    // Admin access
    String CREATE = BASE_ADMIN;
    String UPDATE = BASE_ADMIN;

    // Public access
    String CHECK = BASE_PUBLIC + "/{id}/check";
    String FETCH_ALL = BASE_PUBLIC;

}
