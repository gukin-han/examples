package com.example.demo.service;

import com.example.demo.exception.CertificationCodeNotMatchedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserStatus;
import com.example.demo.model.dto.UserCreateDto;
import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.repository.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@SqlGroup(
        {
                @Sql(value = "/sql/user-service-test-data.sql", executionPhase =  Sql.ExecutionPhase.BEFORE_TEST_METHOD),
                @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        }
)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private JavaMailSender mailSender;

    @DisplayName("getByEmail은 ACTIVE 상태인 유저를 찾아올 수 있다")
    @Test
    void test() {
        //given
        String email = "test@naver.com";

        //when
        UserEntity result = userService.getByEmail(email);

        //then
        Assertions.assertThat(result.getNickname()).isEqualTo("gukin");
    }

    @DisplayName("getByEmail은 PENDING 상태인 유저를 찾아올 수 없다")
    @Test
    void test2() {
        //given
        String email = "test2@naver.com";

        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            UserEntity result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @DisplayName("getById은 ACTIVE 상태인 유저를 찾아올 수 있다")
    @Test
    void test3() {
        //given
        //when
        UserEntity result = userService.getById(1);

        //then
        Assertions.assertThat(result.getNickname()).isEqualTo("gukin");
    }

    @DisplayName("getById은 PENDING 상태인 유저를 찾아올 수 없다")
    @Test
    void test4() {
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            UserEntity result = userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @DisplayName("userCreateDto를 이용하여 유저를 생성할 수 있다")
    @Test
    void test5(){
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("test4@test.com")
                .address("Gyeongi")
                .nickname("gukin3")
                .build();
        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        //when
        UserEntity result = userService.create(userCreateDto);

        //then
        Assertions.assertThat(result.getId()).isNotNull();
        Assertions.assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
    }

    @DisplayName("userCreateDto를 이용하여 유저를 수정할 수 있다")
    @Test
    void test6(){
        //given
        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .address("Incheon")
                .nickname("gukin4")
                .build();

        //when
        userService.update(1, userUpdateDto);

        //then
        UserEntity userEntity = userService.getById(1);
        Assertions.assertThat(userEntity.getId()).isNotNull();
        Assertions.assertThat(userEntity.getAddress()).isEqualTo("Incheon");
        Assertions.assertThat(userEntity.getNickname()).isEqualTo("gukin4");
    }

    @DisplayName("user를 로그인 시키면 마지막 로그인 시간이 변경된다")
    @Test
    void test7(){
        //given
        //when
        userService.login(1);

        //then
        UserEntity userEntity = userService.getById(1);
        Assertions.assertThat(userEntity.getLastLoginAt()).isGreaterThan(0);
    }

    @DisplayName("PENDING 상태의 사용자는 인증 코드로 ACTIVE 시킬 수 있다")
    @Test
    void test8(){
        //given
        //when
        userService.verifyEmail(2, "aaaaa-aaaaa-aaaaaa-aaaaa-aaaaa-aaab");

        //then
        UserEntity userEntity = userService.getById(1);
        Assertions.assertThat(userEntity.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @DisplayName("PENDING 상태의 사용자는 잘못된 인증 코드를 받으면 에러를 던진다")
    @Test
    void test9(){
        //given
        //when
        //then
        Assertions.assertThatThrownBy(() -> {
            userService.verifyEmail(2, "aaaaa-aaaaa-aaaaaa-aaaaa-aaaaa-aaaC");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }
}