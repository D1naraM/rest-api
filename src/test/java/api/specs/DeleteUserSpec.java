package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class DeleteUserSpec {
    public static RequestSpecification deleteUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().method();

    public static ResponseSpecification deleteUserResponseSpec = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(204)
            .build();
}
