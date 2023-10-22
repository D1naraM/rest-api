package api.basetests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTests extends TestBase {
    @Test
    void checkTotal() {
        given()
                .log().method()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void createUser() {
        String user = "{ \"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("leader"));
    }

    @Test
    void updateUser() {
        String userNewBody = "{ \"name\": \"morpheus\", \"job\": \"plumber\"}";
        given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userNewBody)
                .when()
                .post("/users/428")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("plumber"));
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

        String userNewBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"123456\"}";
        given()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(userNewBody)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id",notNullValue(),
                        "token", notNullValue());

    }
}
