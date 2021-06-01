package com.future.restoapp.unitTest.repository;

import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.OrderItem;
import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.repository.MenuRepository;
import com.future.restoapp.repository.ReservationRepository;
import com.future.restoapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.*;

@DisplayName("Reservation Repository Unit Tests")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservationRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Autowired
    private MenuRepository menuRepository;
    private Menu menu1;
    private Menu menu2;

    @Autowired
    private ReservationRepository reservationRepository;
    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    public void init() {
        user = User.builder()
                .username("hello")
                .email("hello@g.com")
                .password("123456")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

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

    @DisplayName("Successfully create a new and complete reservation data")
    @Test
    public void saveSuccess() throws Exception {
        System.out.println(user);

        userRepository.save(user);

        System.out.println(user);

        System.out.println(menu1);
        System.out.println(menu2);

        menuRepository.save(menu1);
        menuRepository.save(menu2);

        System.out.println(menu1);
        System.out.println(menu2);

        Set<OrderItem> orders = new HashSet<>();
//        orders.add();

        Reservation reservation = Reservation.builder()
                                    .user(user)
                                    .startTime(LocalDateTime.now())
                                    .orders(orders)
                                    .build();

        reservationRepository.save(reservation);

        System.out.println(reservation);
//        assertThat(addedMenu).isEqualTo(menu1);
    }

}
