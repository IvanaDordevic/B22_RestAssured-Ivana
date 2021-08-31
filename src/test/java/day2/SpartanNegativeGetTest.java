package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpartanNegativeGetTest {


    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
      baseURI = "http://35.170.70.50:8000";
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("GET request to /api/spartans/10")
    @Test
            public static void test1(){


    Response response = given().accept(ContentType.XML).log().all()
            .when().get("/api/spartans/10");

    assertEquals("406", response.statusCode());
    assertEquals("application/xml;charset=UTF-8", response.contentType());




   }
}
