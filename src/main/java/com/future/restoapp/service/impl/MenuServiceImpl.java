package com.future.restoapp.service.impl;

import com.future.restoapp.domain.Menu;
import com.future.restoapp.domain.Menu.MenuCategory;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu create(@NotNull Menu menu) throws Exception {
        return menuRepository.save(menu);
    }

    @Override
    public Menu findById(long id) throws Exception {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Menu> findByQuery(String name, MenuCategory category, Boolean isSold, Pageable pageable) throws Exception{
        Menu menu = Menu
                .builder()
                .name(name)
                .category(category)
                .isSold(isSold)
                .build();

        menu.setCreatedDate(null);
        menu.setUpdatedDate(null);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("isSold", ExampleMatcher.GenericPropertyMatchers.exact());

        return menuRepository.findAll(Example.of(menu, exampleMatcher), pageable);
    }

    @Override
    public void deleteById(long id) throws Exception {
        menuRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with specified ID not found"));
        menuRepository.deleteById(id);
    }

    @Override
    public Menu update(@NotNull Menu menu) throws Exception {
        Menu menuDb = menuRepository
                .findById(menu.getId())
                .orElseThrow(() -> new NoSuchElementException("Menu with specified ID not found"));
        menuDb.update(menu);
        return menuRepository.save(menuDb);
    }

}
