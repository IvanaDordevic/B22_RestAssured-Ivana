package myPractice;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SpartanTestWithQueryParams {
@BeforeAll
    public static void setUpClass(){


    baseURI = "http://44.198.60.193:8000";
    }
    /*
    given accept type is json
    and query params values are:
    gender|Female
    nameContains|e
    when user sends get request to api/spartans/search
    then response status code is 200
    response content type is application/jason
    and "Female" should be in response
    and" Janette" should be in response
     */

    @Test
    public void test1(){
        
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("gender", "Female")
                .and().queryParams("q", "e")
                .when().get("api/spartans/search");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void test2(){

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("q", "e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("api/spartans/search");


        assertEquals(response.statusCode(), 200);

        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));


    }

}
