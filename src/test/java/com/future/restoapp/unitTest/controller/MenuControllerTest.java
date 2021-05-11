package com.future.restoapp.unitTest.controller;

import com.future.restoapp.controller.MenuController;
import com.future.restoapp.controller.path.MenuControllerPath;
import com.future.restoapp.model.dto.MenuCreateRequest;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.service.MenuService;
import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.future.restoapp.unitTest.controller.AbstractControllerTest.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Menu Controller Unit Tests")
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
class MenuControllerTest {

    @InjectMocks
    private MenuController menuController;

    @Mock
    private MenuService menuService;

    private MockMvc mockMvc;

    private MenuCreateRequest request;

    private Menu menu1;
    private Menu menu2;
    private Menu menu3;

    private List<Menu> menuList;

    @BeforeEach
    public void init(){
        menuList = new ArrayList<>();
        mockMvc = MockMvcBuilders
                .standaloneSetup(menuController)
                .build();

        request = MenuCreateRequest.builder()
                .name("bipang")
                .category("food")
                .price(20333F)
                .stock(8)
                .description("extreme meal")
                .build();

        menu3 = Menu.builder()
                .name("bipang")
                .category("food")
                .price(20333F)
                .stock(8)
                .description("extreme meal")
                .build();
        menu3.setId("3");

        menu1 = Menu.builder()
                .name("kiranti")
                .category("drink")
                .price(12345F)
                .stock(12)
                .description("so good")
                .build();
        menu1.setId("1");

        menu2 = Menu.builder()
                .name("superjoss")
                .category("drink")
                .price(1345F)
                .stock(1200)
                .description("so nice")
                .build();
        menu2.setId("2");

        menuList.add(menu1);
        menuList.add(menu2);
    }

    @AfterEach
    public void tearDown() {
        request = null;
        menuList = null;
        menu1 = menu2 = menu3 = null;
    }

    @DisplayName("Create Menu Success")
    @Test
    public void createMenuSuccess() throws Exception {
        doAnswer(invocation -> {
            Menu menu = invocation.getArgument(0);

            if(menuList.contains(menu)){
                throw new RuntimeException();
            } else {
                menuList.add(menu);
            }

            return null;
        }).when(menuService).create(any());

        mockMvc.perform(
                    post(MenuControllerPath.CREATE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(request))
                ).andExpect(status().isCreated());
//                .andDo(print());

        verify(menuService, times(1)).create(any());
    }

    @DisplayName("Delete Menu Success")
    @Test
    public void deleteMenuSuccess() throws Exception {
        doAnswer(invocation -> {
            String id = invocation.getArgument(0);
            Menu menu = null;
            boolean exist = false;

            for(Menu m: menuList){
                exist = m.getId().equals(id);

                if(exist){
                    menu = m;
                    break;
                }
            }

            if(!exist){
                throw new RuntimeException();
            }

            return menu;
        }).when(menuService).deleteById(anyString());

        String id = "1";

        mockMvc.perform(
                delete(MenuControllerPath.DELETE, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(menu1)))
                .andDo(print());

        verify(menuService, times(1)).deleteById(any());
    }

    @DisplayName("Delete Menu Fail - Not Exist ID")
    @Test
    public void deleteMenuFailNotExistID() throws Exception {
        doAnswer(invocation -> {
            String id = invocation.getArgument(0);
            Menu menu = null;
            boolean exist = false;

            for(Menu m: menuList){
                exist = m.getId().equals(id);

                if(exist){
                    menu = m;
                    break;
                }
            }

            if(!exist){
//                throw new SQLGrammarException("", new SQLException());
                throw new SQLGrammarException("", new SQLException());
            }

            return menu;
        }).when(menuService).deleteById(anyString());

        String id = "12345";

        // TODO: Fix error handler not catch error
        mockMvc.perform(
                delete(MenuControllerPath.DELETE, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        verify(menuService, times(1)).deleteById(any());
    }

}
