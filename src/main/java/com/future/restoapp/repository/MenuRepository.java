package com.future.restoapp.repository;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
