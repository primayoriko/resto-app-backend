package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {

    void deleteByName(String name) throws Exception;

    Page<Menu> findAllByOrderByNameAsc(Pageable pageable) throws Exception;

    Page<Menu> findAllByNameContainingOrderByNameAsc(String name, Pageable pageable) throws Exception;

    Page<Menu> findAllByCategoryLikeOrderByNameAsc(String category, Pageable pageable) throws Exception;

}