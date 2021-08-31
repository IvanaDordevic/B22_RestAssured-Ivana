package day3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpartanTestWithParameters {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://35.170.70.50:8000";
    }


  /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */


    @DisplayName("GET request to API Spartans ID 5")
    @Test
    public void test1() {


        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 5)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));
    }
  /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

@DisplayName("GET request to API Spartans ID 500")
    @Test
    public void test2(){

    Response response = given().accept(ContentType.JSON).pathParam("id", 500)
            .when().get("/api/spartans/{id}");
    assertEquals(404, response.statusCode());
    assertEquals("application/json", response.contentType());
    assertTrue(response.body().asString().contains("Not Found"));
}

@DisplayName("GET request to API Spartans search")
    @Test
    public void test3(){
   /* Given accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
            */

    Response response = given().log().all().accept(ContentType.JSON)
            .and().queryParam("nameContains", "e")
            .and().queryParam("gender","Female")
            .when().get("/api/spartans/search");

    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());
    assertTrue(response.body().asString().contains("Female"));
    assertTrue(response.body().asString().contains("Janette"));

}

    @DisplayName("GET request to API Spartans search MAP")
    @Test
    public void test4(){

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }
}
