package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    void create(Menu menu) throws Exception;

    void deleteByName(String name) throws Exception;

    List<Menu> findByNameContainingOrderByNameAsc(String name) throws Exception;

    //    void updateById(String id, Menu menu) throws Exception;

}