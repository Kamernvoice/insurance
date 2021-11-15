package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.User;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setName("test");
        user.setLogin("test@test.com");
        user.setPassword("test");
        user.setPhone(258730493);

        userRepository.save(user);
        User foundUser = userRepository.findByLogin(user.getLogin());
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(foundUser.getName(), user.getName());
        Assertions.assertEquals(foundUser.getLogin(), user.getLogin());
        Assertions.assertEquals(foundUser.getPassword(), user.getPassword());
        Assertions.assertEquals(foundUser.getPhone(), user.getPhone());
        userRepository.delete(foundUser);
    }
}
