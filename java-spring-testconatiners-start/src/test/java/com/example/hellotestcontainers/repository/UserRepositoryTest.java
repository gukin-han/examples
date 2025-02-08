package com.example.hellotestcontainers.repository;

import com.example.hellotestcontainers.AbstractIntegrationTest;
import com.example.hellotestcontainers.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void testContainerTest() {
        // given
        final String email = "testEmail";
        final String username = "testUsername";
        final User user = User.builder()
                .email(email)
                .username(username)
                .build();

        // when
        final User savedUser = userRepository.save(user);

        // then
        assertThat(userRepository.findById(savedUser.getUserId()))
                .isPresent()
                .get()
                .extracting(User::getUserId, User::getEmail, User::getUsername)
                .containsExactly(savedUser.getUserId(), email, username);
    }

    @Test
    void findNothingTest() {
        // given
        // when
        final List<User> users = userRepository.findAll();

        // then
        assertThat(users).hasSize(0);
    }
}