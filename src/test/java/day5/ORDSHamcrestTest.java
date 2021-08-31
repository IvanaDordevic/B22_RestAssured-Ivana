package day5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class ORDSHamcrestTest extends HRTestBase {


    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {

        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli");
        given()
                .accept(ContentType.JSON)
                .pathParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")) //contains with order
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //contains without order
                .body("items.first_name", equalTo(names)); // equality of lists assertion

    }

    @Test
    public void employeesTest2() {

      JsonPath jsonPath =  given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();//method that allowes us to get response object after we use then() method

      jsonPath.prettyPrint();
      //assert that there is only 5 first_names
      assertThat(jsonPath.getList("items.first_name"),hasSize(5));
    //assert first_name order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));



    }
}