package com.future.restoapp.controller;

public interface UserControllerPath {

    String BASE_PATH = "/api/users";
    String FIND_BY_CODE = "/{code}";
    String UPDATE_BY_CODE = "/{code}";
    String DELETE_BY_CODE = "/{code}";
    String UPDATE_NAME_BY_CODE = "/{code}/name";

}
