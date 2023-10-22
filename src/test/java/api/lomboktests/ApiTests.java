package api.lomboktests;

import api.basetests.TestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends TestBase {
    @Test
    void checkTotal() {
        ListUsersResponse response = given()
                .log().method()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ListUsersResponse.class);
        assertEquals(12, response.getTotal());
    }

    @Test
    void createUser() {
        UserBody userData = new UserBody();
        userData.setName("morpheus");
        userData.setJob("leader");

        UserResponse response = given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserResponse.class);

        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
    }

    @Test
    void updateUser() {
        UserBody userData = new UserBody();
        userData.setName("morpheus");
        userData.setJob("plumber");

        UserResponse response = given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("/users/428")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserResponse.class);
        assertEquals("morpheus", response.getName());
        assertEquals("plumber", response.getJob());

    }

    @Test
    void deleteUser() {
        given()
                .log().method()
                .when()
                .delete("/users/845")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void registerUser() {

        RegisterBody registerData = new RegisterBody();
        registerData.setEmail("eve.holt@reqres.in");
        registerData.setPassword("123456");

        RegisterResponse response = given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(registerData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)

                .extract().as(RegisterResponse.class);
        assertEquals(4, response.getId());
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());

    }
}
