package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface ReservationControllerPath{

    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/reservations";
    String BASE_CLIENT = UrlBasePath.CURRENT_CLIENT + "/reservations";
    String BASE_USER = UrlBasePath.CURRENT_USER + "/reservations";

    // Admin access
    String UPDATE_ADMIN = BASE_ADMIN; // Status Only
    String FETCH_ONE_ADMIN = BASE_ADMIN + "/{id}";
    String FETCH_ALL_ADMIN = BASE_ADMIN;

    // Client access
    String CREATE = BASE_CLIENT;
    String UPDATE_CLIENT = BASE_ADMIN; // Status Only
    String DELETE = BASE_CLIENT + "/{id}";
    String FETCH_ONE_CLIENT = BASE_CLIENT + "/{id}";
    String FETCH_OWN_CLIENT = BASE_CLIENT;

    // Client access
    String FETCH_ONE = BASE_USER + "/{id}";
    String FETCH_ALL = BASE_USER;

}
