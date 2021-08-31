package myPractice;

import static io.restassured.RestAssured.baseURI;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanTestWithPathMethod {
@BeforeAll
    public static void setUpClass() {
        baseURI = "http://44.198.60.193:8000";

    }


    /*
    given acept type is Json
    and path param is 10
    when user sends get to /spartans/id
    then status code is 200
    and content type is"application/json"
    and response payload values match:
    id 10, name "Lorenza", gender "Female", phone 3312820936
     */

        @Test
        public void test1(){

            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id", 10)
                    .when().get("api/spartans/{id}");

            assertEquals(response.statusCode(), 200);
            assertEquals(response.contentType(), "application/json");

            System.out.println("Id:" + response.body().path("id").toString());
            System.out.println(("name:" + response.body().path("name").toString()));
            System.out.println(("gender: " + response.body().path("gender").toString()));
            System.out.println(("phone: " + response.body().path("phone").toString()));

            int id = response.path("id");
            String name = response.path("name");
            String gender = response.path("gender");
            long phone = response.path("phone");
            
            
            assertEquals(id, 10);
            assertEquals(name, "Lorenza");
            assertEquals(gender, "Female");
            assertEquals(phone,3312820936l);

        }

        @Test
    public void test2(){

            Response response = get("/api/spartans");

            int firstId = response.path("id[0]");
            System.out.println("firstId = " + firstId);

            String firstName = response.path("name[0]");
            System.out.println("firstName = " + firstName);

            String lastfirstName = response.path("name[-1]");
            System.out.println("lastfirstName = " + lastfirstName);

            List<String> names = response.path("name");
            System.out.println("names = " + names);
            System.out.println("names.size() = " + names.size());

            List<Object> phoneNumber = response.path("phone");
            for (Object each : phoneNumber) {
                System.out.println(phoneNumber);

            }

        }

}
