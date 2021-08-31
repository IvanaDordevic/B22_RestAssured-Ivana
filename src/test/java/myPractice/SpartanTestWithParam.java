package myPractice;

import com.sun.tools.javac.util.Assert;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpartanTestWithParam {

/*
given accept type is json
and id param value is 18
when user sends gET to api/spartans/{id}
then response status code is 200
and response content type application/json...
and "Allen" should be in response payload
 */

    @BeforeAll
    public static void setUpClass() {
        RestAssured.baseURI = "http://44.198.60.193:8000";
    }

    @Test
    public void PathTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 18)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Jennica"));

        response.body().prettyPrint();


        }
        /*
given accept type is json
and id param value is 500
when user sends gET to api/spartans/{id}
then response status code is 404
and response content type application/json...
and Spartan Not Found message should be in response payload
 */

    @Test
    public void negativeParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 404);
        assertTrue(response.body().asString().contains("Not Found"));

response.prettyPrint();


    }
    }





