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
    public void create(Menu menu) throws Exception {
        menuRepository.save(menu);
    }

    @Override
    public void deleteById(String id) throws Exception {
        menuRepository.deleteById(id);
    }

    @Override
    public void updateById(String id, Menu menu) throws Exception {
        Optional<Menu> menuDb = menuRepository.findById(id);

        if(!menuDb.isPresent()) throw new NoSuchElementException("Menu with specified ID not found");

        Menu savedMenu = menuDb.get();

        if(savedMenu.getName() != null) savedMenu.setName(menu.getName());
        if(savedMenu.getCategory() != null) savedMenu.setCategory(menu.getCategory());
        if(savedMenu.getPrice() != null) savedMenu.setPrice(menu.getPrice());
        if(savedMenu.getDescription() != null) savedMenu.setDescription(menu.getDescription());
        if(savedMenu.getStock() != null) savedMenu.setStock(menu.getStock());

        menuRepository.save(savedMenu);
    }

    @Override
    public Menu findOneById(String id) throws Exception {
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
