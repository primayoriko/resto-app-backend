package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    Menu create(Menu menu) throws Exception;

    Menu updateById(Long id, Menu menu) throws Exception;

    Menu deleteById(Long id) throws Exception;

    Menu findOneById(Long id) throws Exception;

    Page<Menu> findAll(String name, MenuCategory category, Boolean isSold, Pageable pageable) throws Exception;

}
