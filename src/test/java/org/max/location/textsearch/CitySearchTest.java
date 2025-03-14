package org.max.location.textsearch;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

public class CitySearchTest extends AccuweatherAbstractTest {

    @Test
    public void testCitySearch() {
        Response response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Missouri")
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/search");
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("LocalizedName", hasItem("Missouri"))
                .body("Country.EnglishName", hasItem("Canada"))
                .body("Region.EnglishName", hasItem("North America"));


        //System.out.println(response.getBody().asString());
    }
}
