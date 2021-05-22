package com.future.restoapp.unitTest.service;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.service.MenuService;
import com.future.restoapp.service.MenuServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Menu Service Unit Tests")
@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @InjectMocks
    private final MenuService menuService = new MenuServiceImpl();

    @Mock
    private MenuRepository menuRepository;

    private Menu menu1;
    private Menu menu2;
    private Menu menu3;

    private List<Menu> menuList;

    @BeforeAll
    public static void initAll(){
        // Define needed first time setup here
    }

    @BeforeEach
    public void init(){
        menuList = new ArrayList<>();

        menu1 = Menu.builder()
                .name("kiranti")
                .category("drink")
                .price(12345F)
                .stock(12)
                .description("so good")
                .build();
        menu1.setId(1L);

        menu2 = Menu.builder()
                .name("superjoss")
                .category("drink")
                .price(1345F)
                .stock(1200)
                .description("so nice")
                .build();
        menu2.setId(2L);

        menu3 = Menu.builder()
                .name("bipang")
                .category("food")
                .price(20333F)
                .stock(8)
                .description("extreme meal")
                .build();
        menu3.setId(3L);

        menuList.add(menu1);
        menuList.add(menu2);
    }

    @AfterEach
    public void tearDown(){
        menu1 = menu2 = menu3 = null;
        menuList = null;
    }

    @DisplayName("Successfully create a new and complete menu data")
    @Test
    public void createSuccess() throws Exception {
        doAnswer(invocation -> {
            Menu menu = invocation.getArgument(0);

            if(menuList.contains(menu)){
                throw new RuntimeException();
            } else {
                menuList.add(menu);
            }

            return null;
        }).when(menuRepository).save(any());

        menuService.create(menu3);
        verify(menuRepository, times(1)).save(menu3);
    }

    @DisplayName("Failed to create an existing data")
    @Test
    public void createFailAlreadyExist() throws Exception {
        doAnswer(invocation -> {
            Menu menu = invocation.getArgument(0);

            if(menuList.contains(menu)){
                throw new RuntimeException();
            } else {
                menuList.add(menu);
            }

            return null;
        }).when(menuRepository).save(any());

        try {
            menuService.create(menu3);
            menuService.create(menu3);
            verify(menuRepository, times(2)).save(menu3);
        } catch (Exception ex) {
            return;
        }
        assert false;
    }

    @DisplayName("Successfully delete entry from existent ID")
    @Test
    public void deleteByIdSuccess() throws Exception {
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            return menuList.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst();
        }).when(menuRepository).findById(anyLong());

        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);

            boolean exist = false;
            Menu menu = menu1;

            for(Menu m: menuList){
                exist = m.getId().equals(id);

                if(exist) {
                    menu = m;
                    break;
                }
            }

            if(!exist) throw new RuntimeException();

            return Optional.of(menu);
        }).when(menuRepository).deleteById(anyLong());

        Menu menu = menuService.deleteById(menu1.getId());

        assertThat(menu).isEqualTo(menu1);
        verify(menuRepository, times(1)).findById(menu1.getId());
        verify(menuRepository, times(1)).deleteById(menu1.getId());
    }

    @DisplayName("Failed to delete entry from inexistent ID")
    @Test
    public void deleteFailNoRecord() throws Exception {
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            return menuList.stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst();
        }).when(menuRepository).findById(anyLong());

        Long id = 456L;

        try {
            menuService.deleteById(id);

        } catch(Exception ex) {
            verify(menuRepository, times(1)).findById(id);
            verify(menuRepository, times(0)).deleteById(id);

            return;
        }

        assert false;
    }

}
