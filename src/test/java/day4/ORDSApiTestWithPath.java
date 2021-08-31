package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ORDSApiTestWithPath extends HRTestBase {
    @DisplayName("GET Request to countries Path Method")
    @Test
    public void test1(){

       Response response = given().accept(ContentType.JSON)
                          .queryParam("q", "{\"region_id\": 2}")
               .when()
               .get("/countries");
       
       assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);


        //get all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert all region id are equal to 2
        List<Integer> allRegionId = response.path("items.region_id");
        for (Integer regionsID : allRegionId) {
            System.out.println("regionsId = "+ regionsID );
        assertEquals(2, regionsID);


        }

    }
    }



