package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StartWithJsonPath extends SpartanTestBase {

@DisplayName("GET one spartan with JsonPath")
@Test
public void test1(){

    Response response = given().accept(ContentType.JSON)
            .and().pathParam("id",10)
            .when().get("/api/spartans/{id}");

    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());



    //print name with path method

    System.out.println(response.path("name").toString());

    //assign response to jsonpath
    JsonPath jsonPath = response.jsonPath();

    int id = jsonPath.getInt("id");
    String name = jsonPath.getString("name");
    String gender = jsonPath.getString("gender");
    long phone = jsonPath.getLong("phone");

    System.out.println("id = " + id);
    System.out.println("name = " + name);
    System.out.println("gender = " + gender);
    System.out.println("phone = " + phone);


}
}
