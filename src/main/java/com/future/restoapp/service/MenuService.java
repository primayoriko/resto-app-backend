package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    void create(Menu menu) throws Exception;

    void updateById(String id, Menu menu) throws Exception;

    void deleteById(String id) throws Exception;

    Menu findById(String id) throws Exception;

    Page<Menu> find(Pageable pageable) throws Exception;

}
