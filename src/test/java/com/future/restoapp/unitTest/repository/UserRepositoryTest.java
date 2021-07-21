package com.future.restoapp.unitTest.repository;

import com.future.restoapp.domain.User;
import com.future.restoapp.repository.UserRepository;
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

@DisplayName("User Repository Unit Tests")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void init() {
        user = User.builder()
                .username("hello")
                .email("hello@g.com")
                .password("123456")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();
    }

    @AfterEach
    public void tearDown() {
        // TODO: Solve flushing problem
//        userRepository.deleteAll();
        user = null;
    }

    @DisplayName("Successfully create a new and complete user data")
    @Test
    public void saveSuccess() throws Exception {
        User addedUser = userRepository.save(user);
        assertThat(addedUser).isEqualTo(user);
    }

    @DisplayName("Failed to create an existing data")
    @Test
    public void saveFailAlreadyExist() throws Exception {
        User addedUser = userRepository.save(user);
        assertThat(addedUser).isEqualTo(user);

        User user2 = User.builder()
                .username("hello")
                .email("hello@g.com")
                .password("123456")
                .hpNumber("082116235723")
                .isAdmin(false)
                .build();

        try {
            addedUser = userRepository.save(user2);
            assertThat(addedUser).isEqualTo(user2);
        } catch (Exception ex) {
//            user = user2 = null;
//            System.out.println(ex.getClass());
            return;
        }

        assert false;
    }

}
