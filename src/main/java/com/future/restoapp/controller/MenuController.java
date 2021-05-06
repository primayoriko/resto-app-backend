package com.future.restoapp.controller;

import com.future.restoapp.model.dto.MenuCreateRequest;
import com.future.restoapp.model.dto.MenuUpdateRequest;
import com.future.restoapp.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Menu")
@RestController
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = MenuControllerPath.CREATE, method = RequestMethod.POST)
    public ResponseEntity create(@Valid @RequestBody MenuCreateRequest menuReq){
        return null;
    }

    @RequestMapping(value = MenuControllerPath.UPDATE, method = RequestMethod.PATCH)
    public ResponseEntity update(@Valid @RequestBody MenuUpdateRequest menuReq){
        return null;
    }

    @RequestMapping(value = MenuControllerPath.DELETE, method = RequestMethod.DELETE)
    public ResponseEntity delete(@Valid @PathVariable String id){
        return null;
    }

    @RequestMapping(
            value = {
                    MenuControllerPath.FETCH_ONE_ADMIN,
                    MenuControllerPath.FETCH_ONE_CLIENT
            },
            method = RequestMethod.GET
    )
    public ResponseEntity fetchOne(@Valid @PathVariable String id){
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
