package myPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanTests {

    String spartanUrl= "http://44.193.10.251:8000";
    @Test
    public void test1(){
       Response response = RestAssured.get(spartanUrl + "/api/spartans");

        System.out.println(response.statusCode());
        System.out.println(response.body().prettyPrint());

    }
    @Test
    public void test2(){
       Response response = RestAssured.get(spartanUrl + "/api/spartans");
        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertTrue(response.body().asString().contains("Allen"));


    }
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanUrl + "/api/spartans");

        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(),"application/json");



    }

}
