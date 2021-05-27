package com.future.restoapp.controller.path;

import com.future.restoapp.constant.UrlBasePath;

public interface ReservationControllerPath {
    String BASE_CLIENT = UrlBasePath.CURRENT_CLIENT + "/reservations";
    String BASE_ADMIN = UrlBasePath.CURRENT_ADMIN + "/reservations";

    // Admin access
    String UPDATE = BASE_ADMIN; // Status Only
    String FETCH_ONE_ADMIN = BASE_ADMIN + "/{id}";
    String FETCH_ADMIN = BASE_ADMIN;

    // Client access
    String CREATE = BASE_CLIENT;
    String DELETE = BASE_CLIENT + "/{id}";
    String FETCH_ONE_CLIENT = BASE_CLIENT + "/{id}";
    String FETCH_OWN_CLIENT = BASE_CLIENT;

}
