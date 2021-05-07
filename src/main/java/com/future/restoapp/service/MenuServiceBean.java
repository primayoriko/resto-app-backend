package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceBean implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public void create(Menu menu) throws Exception {
        menuRepository.create(menu);
    }

    @Override
    public void updateById(String id, Menu menu) throws Exception {

    }

    @Override
    public void deleteById(String id) throws Exception {

    }

    @Override
    public Menu findById(String id) throws Exception {
        return null;
    }

    @Override
    public Page<Menu> find(Pageable pageable) throws Exception {
        return null;
    }


}
