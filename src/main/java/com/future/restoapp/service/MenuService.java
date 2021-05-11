package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    void create(Menu menu) throws Exception;

    void updateById(String id, Menu menu) throws Exception;

    Menu deleteById(String id) throws Exception;

    Menu findOneById(String id) throws Exception;

    Page<Menu> findAllByNameAndCategory(String name, String category, Pageable pageable) throws Exception;

    Page<Menu> findAllByName(String name, Pageable pageable) throws Exception;

    Page<Menu> findAllByCategory(String category, Pageable pageable) throws Exception;

    Page<Menu> findAll(Pageable pageable) throws Exception;

}
