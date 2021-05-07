package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {
    Menu findByName(String name) throws Exception;

    void create(Menu menu) throws Exception;

    void update(Menu menu) throws Exception;

    void deleteByName(String name) throws Exception;

}