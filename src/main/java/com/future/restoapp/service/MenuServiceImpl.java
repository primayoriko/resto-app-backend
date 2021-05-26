package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceBean.class);

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu create(Menu menu) throws Exception {
        System.out.println(menu);
        return menuRepository.save(menu);
    }

    @Override
    public Menu deleteById(Long id) throws Exception {
        Menu menu = menuRepository.findById(id).orElse(null);

        if(menu == null) throw new NoSuchElementException("Menu with specified ID not found");

        menuRepository.deleteById(id);

        return menu;
    }

    @Override
    public Menu updateById(Long id, Menu menu) throws Exception {
        Optional<Menu> menuDb = menuRepository.findById(id);

        if(!menuDb.isPresent()) throw new NoSuchElementException("Menu with specified ID not found");

        Menu savedMenu = menuDb.get();

        if(menu.getPrice() != null) savedMenu.setPrice(menu.getPrice());
        if(menu.getIsSold() != null) savedMenu.setIsSold(menu.getIsSold());

        return menuRepository.save(savedMenu);
    }

    @Override
    public Menu findOneById(Long id) throws Exception {
        Optional<Menu> menu = menuRepository.findById(id);

        return menu.orElse(null);
    }

    @Override
    public Page<Menu> findAllByNameAndCategory(String name, String category, Pageable pageable) throws Exception{
        Page<Menu> result = null;

        if(name != null && category != null) {
            result = menuRepository.findAllByNameContainingAndCategoryLikeOrderByCategoryAscNameAsc(name, category, pageable);
        } else if(name != null) {
            result = menuRepository.findAllByNameContainingOrderByCategoryAscNameAsc(name, pageable);
        } else if(category != null) {
            result = menuRepository.findAllByCategoryLikeOrderByCategoryAscNameAsc(category, pageable);
        } else {
            result = menuRepository.findAllByOrderByNameAsc(pageable);
        }

        return result;
    }

    @Override
    public Page<Menu> findAllByName(String name, Pageable pageable) throws Exception {
        return findAllByNameAndCategory(name, null, pageable);
    }

    @Override
    public Page<Menu> findAllByCategory(String category, Pageable pageable) throws Exception {
        return findAllByNameAndCategory(null, category, pageable);
    }

    @Override
    public Page<Menu> findAll(Pageable pageable) throws Exception {
        return findAllByNameAndCategory(null, null, pageable);
    }

}
