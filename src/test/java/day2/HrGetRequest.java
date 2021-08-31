package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions.*;
public class HrGetRequest {

    //BeforeAll is an annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
       baseURI = "http://35.170.70.50:1000/ords/hr";

    }
    @DisplayName("GET request to regions")
    @Test
    public void test1(){
        Response response = get("/regions");
        System.out.println(response.statusCode());

    }
     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */
    @DisplayName("Regions request")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).when().get("/regions");
        assertEquals(response.statusCode(), 200);
       assertEquals(response.contentType(), "application/json");
       assertEquals(response.body().asString().contains("Americas"), true);

    }
}
