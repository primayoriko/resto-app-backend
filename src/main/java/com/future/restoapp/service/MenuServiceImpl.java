package com.future.restoapp.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
import com.future.restoapp.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Page<Menu> findAll(String name, MenuCategory category, Boolean isSold, Pageable pageable) throws Exception{
        Menu menu = Menu.builder()
                .name(name)
                .category(category)
                .isSold(isSold)
                .build();

        menu.setCreatedDate(null);
        menu.setUpdatedDate(null);
//        System.out.println(menu.getId());
//        System.out.println(menu.getUpdatedDate());
//        System.out.println(menu.getCreatedDate());
//        System.out.println(menu);
//        Collection<Menu> result = menuRepository.findAll(Example.of(menu));
//        Page<Menu> result2 = menuRepository.findAll(Example.of(menu), pageable);
//        System.out.println(result);
//        System.out.println(result2);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("isSold", ExampleMatcher.GenericPropertyMatchers.exact());

        return menuRepository.findAll(Example.of(menu, exampleMatcher), pageable);
    }

}
