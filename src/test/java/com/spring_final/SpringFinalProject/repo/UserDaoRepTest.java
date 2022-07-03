package com.spring_final.SpringFinalProject.repo;

import com.spring_final.SpringFinalProject.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserDaoRepTest {

    @Autowired
    private UserDaoRep underTestUserRep;

    @AfterEach
    void tearDown() {
        underTestUserRep.deleteAll();
    }

    @Test
    void itShouldFindUserById() {
        // given
        User expectedUser = new User(1, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        underTestUserRep.save(expectedUser);

        // when
        User actualUser = null;
        Optional<User> opt = underTestUserRep.findById(1);
        if(opt.isPresent())
            actualUser = opt.get();

        // then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void itShouldDeleteUserById() {
        // given
        User user = new User(2, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        underTestUserRep.save(user);

        // when
        underTestUserRep.deleteById(2);
        List<User> users = underTestUserRep.findAll();
        boolean actual = users.isEmpty();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void itShouldFindAllUsers() {
        // given
        User John = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        User Ben = new User(null, "Ben", "Stiller", "ben", "1234", 56, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        User Johnny = new User(null, "Johnny", "Depp", "johnny", "1234", 45, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        underTestUserRep.save(John);
        underTestUserRep.save(Ben);
        underTestUserRep.save(Johnny);
        List<User> expectedUserList = new ArrayList<>(Arrays.asList(John, Ben, Johnny));

        // when
        List<User> actualUserList = underTestUserRep.findAll();

        // then
        assertThat(actualUserList).isEqualTo(expectedUserList);
    }

    @Test
    void itShouldFindNoUsers() {
        // given
        List<User> expectedUserList = new ArrayList<>();

        // when
        List<User> actualUserList = underTestUserRep.findAll();

        // then
        assertThat(actualUserList).isEqualTo(expectedUserList);
    }

    @Test
    void itShouldNotFindAnyUserById() {
        // given nothing

        // when
        User actualUser = null;
        Optional<User> opt = underTestUserRep.findById(1);
        if(opt.isPresent())
            actualUser = opt.get();

        // then
        assertThat(actualUser).isNull();
    }

    @Test
    void itShouldGetUserByUsername() {
        // given
        User expectedUser = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        underTestUserRep.save(expectedUser);

        // when
        User actualUser = underTestUserRep.getByUsername("john");

        // then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void itShouldNotGetAnyUserByUsername() {
        // given nothing

        // when
        User actualUser = underTestUserRep.getByUsername("john");

        // then
        assertThat(actualUser).isNull();
    }

    @Test
    void testFindAll() {
        // given
        // TODO: check what is this
    }

    @Test
    void itShouldSaveUser() {
        // given
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        // when
        underTestUserRep.save(user);
        boolean actual = underTestUserRep.getByUsername("john") != null;

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void itShouldDeleteUser() {
        // given
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());
        underTestUserRep.save(user);

        // when
        underTestUserRep.delete(user);
        boolean actual = underTestUserRep.getByUsername("john") == null;

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void itShouldNotDeleteUserAsUserIsNotRepresented() {
        // given
        User user = new User(null, "John", "Travolta", "john", "1234", 67, "Male", "+380970689690", new HashSet<>(), new HashSet<>(), new HashSet<>());

        // when
        underTestUserRep.delete(user);
        boolean actual = underTestUserRep.getByUsername("john") == null;

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void itShouldDeleteAuthorities() {
        // given
        // TODO: implement this test
    }

}