package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class UserSpec {
        public static RequestSpecification userRequestSpec = with()
                .filter(withCustomTemplates())
                .log().method()
                .log().body()
                .contentType(JSON);

        public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(201)
                .build();
}
