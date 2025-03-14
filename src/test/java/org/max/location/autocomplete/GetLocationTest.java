package org.max.location.autocomplete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.geoposition.Locations;


import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest {

    @Test
    void getLocation(){

        List<Locations> result = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Samara")
                .when()
                .get(getBaseUrl()+ "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList(".", Locations.class);

        Assertions.assertEquals(10, result.size());
    }
}
