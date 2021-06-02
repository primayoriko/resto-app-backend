package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface MenuService {

    Menu create(@NotNull Menu menu) throws Exception;

    Menu findById(long id) throws Exception;

    Page<Menu> findByQuery(String name, MenuCategory category, Boolean isSold, Pageable pageable) throws Exception;

    void deleteById(long id) throws Exception;

    Menu update(@NotNull Menu menu) throws Exception;

}
