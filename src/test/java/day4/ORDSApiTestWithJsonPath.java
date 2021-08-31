package day4;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.HRTestBase;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;



public class ORDSApiTestWithJsonPath extends HRTestBase {
    @DisplayName("GET Request to countries JsonPath ")
    @Test
    public void test1(){

        final Response response = get("/countries");

        //get 2nd country name with json path- assign response to json path first
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id

        List<String> allCountriesId = jsonPath.getList("items[1].country_name");
        System.out.println(allCountriesId);

        //get all country names where their region id is equal to 2

        List<String> countryId2 = jsonPath.getList("items.findAll{it.region-id==2}.country_name");
        System.out.println("countryId2 = " + countryId2);


    }


}
