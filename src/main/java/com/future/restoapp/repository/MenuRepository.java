package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {

    Page<Menu> findAllByOrderByNameAsc(Pageable pageable) throws Exception;

    Page<Menu> findAllByNameContainingAndCategoryLikeOrderByCategoryAscNameAsc(String name, String category, Pageable pageable) throws Exception;

    Page<Menu> findAllByNameContainingOrderByCategoryAscNameAsc(String name, Pageable pageable) throws Exception;

    Page<Menu> findAllByCategoryLikeOrderByCategoryAscNameAsc(String category, Pageable pageable) throws Exception;

    void deleteByName(String name) throws Exception;

}