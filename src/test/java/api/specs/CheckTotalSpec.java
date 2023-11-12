package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class CheckTotalSpec {
    public static RequestSpecification checkTotalRequestSpec = with()
            .filter(withCustomTemplates())
            .log().method();

    public static ResponseSpecification checkTotalResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();
}

