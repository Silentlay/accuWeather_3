package org.max.location.textsearch;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.locationKey.LocationKey;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TextSearchTest extends AccuweatherAbstractTest {

    @Test
    void getTextSearchCity(){

        List<LocationKey> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Missouri")
                .queryParam("offset", 1)
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2500L))
                .extract()
                .body().jsonPath().getList(".", LocationKey.class);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("Missouri", response.get(0).getEnglishName());
    }
}
