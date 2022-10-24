package com.arturces.springcourseapi.repository;


import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.domain.enums.Role;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    //@Test
    @Ignore
    public void AsaveTest() {
        User user = new User(null, "Kevin", "kevin.wingi@gmail.com", "123", Role.ADMINISTRATOR, null, null);
        User createdUser = userRepository.save(user);

        assertThat(createdUser.getId()).isEqualTo(1L);

    }

    //@Test
    @Ignore
    public void updateTest() {
        User user = new User(1L, "Kevin Wingi", "kevin.wingi@gmail.com", "123", Role.ADMINISTRATOR, null, null);
        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getName()).isEqualTo("Kevin Wingi");
    }

    //@Test
    @Ignore
    public void getByIdTest() {
        Optional<User> result = userRepository.findById(1L);
        User user = result.get();

        assertThat(user.getPassword()).isEqualTo("123");
    }

    //@Test
    @Ignore
    public void listTest() {
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
    }

    //@Test
    @Ignore
    public void loginTest() {
        Optional<User> result = userRepository.login("kevin.wingi@gmail.com", "123");
        User loggedUser = result.get();

        assertThat(loggedUser.getId()).isEqualTo(1L);
    }

    @Test
    public void updateRoleTest (){
       int affectedRows = userRepository.updateRole(1L,Role.SIMPLE);
       assertThat(affectedRows).isEqualTo(1);

    }


}


