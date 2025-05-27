package com.jira.springboot;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
public class RestAssuredRequests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://jsonmock.hackerrank.com/api/countries";
    }

    @Test
    public void getRequestWithQueryParam() {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("name", "France")
                .when()
                .get()
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

        System.out.println( response.getBody().prettyPrint());
        //Assertions.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));
    }

    @org.junit.Test
    public void contextLoads() {
    }

}
