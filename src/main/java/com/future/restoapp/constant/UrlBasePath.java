package com.future.restoapp.constant;

public interface UrlBasePath {
    String V1 = "/api/v1";

    String CURRENT_BASE = V1;

    /* Specific role needed */
    String CURRENT_ADMIN = CURRENT_BASE + "/admin";
    String CURRENT_CLIENT = CURRENT_BASE + "/client";

    /* No authentication needed */
    String CURRENT_PUBLIC = CURRENT_BASE + "/public";

    /* Only authentication needed */
    String CURRENT_USER = CURRENT_BASE + "/user";

}
