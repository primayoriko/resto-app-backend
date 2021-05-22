package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    void create(Menu menu) throws Exception;

    void updateById(Long id, Menu menu) throws Exception;

    Menu deleteById(Long id) throws Exception;

    Menu findOneById(Long id) throws Exception;

    Page<Menu> findAllByNameAndCategory(String name, String category, Pageable pageable) throws Exception;

    Page<Menu> findAllByName(String name, Pageable pageable) throws Exception;

    Page<Menu> findAllByCategory(String category, Pageable pageable) throws Exception;

    Page<Menu> findAll(Pageable pageable) throws Exception;

}
