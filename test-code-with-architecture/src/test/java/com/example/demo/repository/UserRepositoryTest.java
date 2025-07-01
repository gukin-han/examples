package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@DataJpaTest
@Sql("/sql/user-repository-test-data.sql")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("findByIdAndStatus로 유저데이터를 찾아올 수 있다")
    @Test
    void test2(){
        //given
        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @DisplayName("findByIdAndStatus는 데이터가 없으면 Optional empty를 내려준다")
    @Test
    void test3(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.PENDING);

        //then
        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    @DisplayName("findByEmailAndStatus로 유저데이터를 찾아올 수 있다")
    @Test
    void test4(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("test@naver.com", UserStatus.ACTIVE);

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @DisplayName("findByEmailAndStatus는 데이터가 없으면 Optional empty를 내려준다")
    @Test
    void test5(){
        //given

        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("test@naver.com", UserStatus.PENDING);

        //then
        Assertions.assertThat(result.isEmpty()).isTrue();
    }
}