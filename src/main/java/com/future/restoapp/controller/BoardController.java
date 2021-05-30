package com.future.restoapp.controller;

import com.future.restoapp.controller.path.BoardControllerPath;
import com.future.restoapp.model.entity.Board;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class BoardController extends BaseController {

    @RequestMapping(value = BoardControllerPath.CREATE, method = RequestMethod.POST)
    public void create(@Valid @RequestBody Board boardReq) throws Exception {
        
    }

    @RequestMapping(value = BoardControllerPath.CHECK, method = RequestMethod.GET)
    public void check(@PathVariable String id, @NotNull @DateTimeFormat String StartDate,
                      @NotNull @DateTimeFormat String endDate) throws Exception {

    }

    @RequestMapping(value = BoardControllerPath.FETCH_ALL, method = RequestMethod.GET)
    public void fetchAll() throws Exception {

    }

}
