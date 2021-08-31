package day6;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Employee;
import pojo.Region;
import pojo.Regions;
import utilities.HRTestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPPojoGetRequestTest extends HRTestBase {


    @Test
    public void regionTest() {

        /*
        "items": [
        {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://44.198.60.193:1000/ords/hr/regions/1"
         */


        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();
        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {


        Employee employee = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee);

    }
      /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields

     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest() {
        //send a get request and save everthing inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path so as() method is enough
        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        assertThat(regions.getCount(),is(4));
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        List<Region> items = regions.getItems();

        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());

        }

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");


        //compare two result
        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));
    }


}

