package com.future.restoapp.controller;

import com.future.restoapp.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Menu")
@RestController
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = MenuControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(){
        return null;
    }

    @RequestMapping(value = MenuControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(){
        return null;
    }

    @RequestMapping(value = MenuControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id){
        return null;
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ONE_ADMIN,
                    MenuControllerPath.FETCH_ONE_CLIENT
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchOne(@PathVariable String id){
        return null;
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ADMIN,
                    MenuControllerPath.FETCH_CLIENT
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetch(){
        return null;
    }
}
