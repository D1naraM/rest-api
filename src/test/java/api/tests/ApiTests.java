package api.tests;

import api.models.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static api.specs.CheckTotalSpec.checkTotalRequestSpec;
import static api.specs.CheckTotalSpec.checkTotalResponseSpec;
import static api.specs.DeleteUserSpec.deleteUserRequestSpec;
import static api.specs.DeleteUserSpec.deleteUserResponseSpec;
import static api.specs.UserSpec.userRequestSpec;
import static api.specs.UserSpec.userResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTests extends TestBase {
    @Test
    void checkTotal() {
        ListUsersResponse response = step("Отправка get-запроса", () ->
                given(checkTotalRequestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(checkTotalResponseSpec)
                .extract().as(ListUsersResponse.class));

        step("Верификация ответа", () -> {
            assertEquals(12, response.getTotal());
            assertThat(response.getData()
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(user -> user.getId() == 10)
                    .findFirst()
                    .get()
                    .getEmail()).isEqualTo("byron.fields@reqres.in");
        });
    }

    @Test
    void createUser() {
        UserBody userData = new UserBody();
        userData.setName("morpheus");
        userData.setJob("leader");

        UserResponse response = step("Отправка запроса на создание пользователя", () ->
                given(userRequestSpec)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponse.class));

        step("Верификация ответа", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    void updateUser() {
        UserBody userData = new UserBody();
        userData.setName("morpheus");
        userData.setJob("plumber");

        UserResponse response = step("Отправка запроса на изменение пользователя", () ->
                given(userRequestSpec)
                .body(userData)
                .when()
                .post("/users/428")
                .then()
                .spec(userResponseSpec)
                .extract().as(UserResponse.class));

        step("Верификация ответа", () -> {
        assertEquals("morpheus", response.getName());
        assertEquals("plumber", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());
    });

    }

    @Test
    void deleteUser() {
        step("Удаление пользователя", () ->
                given(deleteUserRequestSpec)
                .when()
                .delete("/users/845")
                .then()
                .spec(deleteUserResponseSpec));
    }

    @Test
    void registerUser() {

        RegisterBody registerData = new RegisterBody();
        registerData.setEmail("eve.holt@reqres.in");
        registerData.setPassword("123456");

        RegisterResponse response = step("Отправка запроса на регистрацию пользователя", () ->
                given(userRequestSpec)
                .body(registerData)
                .when()
                .post("/register")
                .then()
                .spec(checkTotalResponseSpec)
                .extract().as(RegisterResponse.class));

        step("Верификация ответа", () -> {
            assertEquals(4, response.getId());
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }
}
