package myPractice;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanTestWithJsonPath {

    @BeforeAll
    public static void setUpClass() {
        baseURI = "http://44.198.60.193:8000";

    }
/*
    given acept type is Json
    and path param is 11
    when user sends get to /spartans/id
    then status code is 200
    and content type is json
    and response payload values match:
    id 11, name "Nona", gender "Female", phone 7959094216
     */

@Test
    public  void test1(){

    Response response = given().accept(ContentType.JSON)
            .pathParam("id", 11)
            .when().get("/api/spartans/{id}");

    assertEquals(response.statusCode(), 200);


    int id = response.path("id");
    System.out.println("id = " + id);

    JsonPath jsonPath = response.jsonPath();
    jsonPath.getInt("id");
    String name = jsonPath.getString("name");
    String gender = jsonPath.getString("gender");
    long phone = jsonPath.getLong("phone");





}
}
