package com.future.restoapp.unitTest.repository;

import com.future.restoapp.domain.Menu;
import com.future.restoapp.repository.MenuRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Menu Repository Unit Tests")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;
    private Menu menu1;
    private Menu menu2;

    @BeforeEach
    public void init() {
        menu1 = Menu.builder()
                .name("kiranti")
                .category(Menu.MenuCategory.DRINK)
                .price(12345F)
                .description("so good")
                .build();

        menu2 = Menu.builder()
                .name("superjoss")
                .category(Menu.MenuCategory.DRINK)
                .price(1345F)
                .description("so nice")
                .build();
    }

    @AfterEach
    public void tearDown() {
        // TODO: Solve flushing problem
//        menuRepository.deleteAll();
        menu1 = menu2 = null;
    }

    @DisplayName("Successfully create a new and complete menu data")
    @Test
    public void saveSuccess() throws Exception {
        Menu addedMenu = menuRepository.save(menu1);
        assertThat(addedMenu).isEqualTo(menu1);
    }

    @DisplayName("Successfully create a new and complete menu data")
    @Test
    public void deleteByIdSuccess() throws Exception {
        Menu addedMenu = menuRepository.save(menu1);
        assertThat(addedMenu).isEqualTo(menu1);

        menuRepository.deleteById(menu1.getId());
    }

    @DisplayName("Failed to delete an unknown ID")
    @Test
    public void deleteByIdFailUnknownID() throws Exception {
        try {
            menuRepository.deleteById(12342132L);
        } catch (Exception ex) {
//            System.out.println(ex.getClass());
            return;
        }

        assert false;
    }

}