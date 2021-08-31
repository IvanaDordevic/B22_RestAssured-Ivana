package day5;
import groovy.lang.DelegatesTo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class HamcrestMatchersApiTest {


    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {
         /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

       given().log().all()
               .accept(ContentType.JSON)
               .and().pathParam("id", 15)
               .get("http://44.198.60.193:8000/api/spartans/{id}")
               .then().statusCode(200)
               .contentType("application/json")
               .and()
               .body("id", is(15),
                       "name",is("Meta"),
                       "gender", is("Female"),
                       "phone",is(1938695106))

                               .log().all();

    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10423)
                .and()

                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length",is("236"))
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Alexander"))
                .body("teachers[0].lastName",is("Syrup"))
                .body("teachers[0].gender",equalTo("male"));

    }
    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        given()
                .accept(ContentType.JSON)

                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName", hasItems("Alexander", "Darleen", "Sean"));






    }

    }