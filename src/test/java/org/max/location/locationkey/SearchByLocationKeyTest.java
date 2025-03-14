package org.max.location.locationkey;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.locationKey.LocationKey;

import static io.restassured.RestAssured.given;

public class SearchByLocationKeyTest extends AccuweatherAbstractTest {

    @Test
    void getSearchByLocationKeyTest(){
        LocationKey response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/620")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response()
                .body().as(LocationKey.class);
        Assertions.assertEquals("Porcheville", response.getLocalizedName());
    }
}
