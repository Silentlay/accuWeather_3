package org.max.Indices.metadata;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class MetadataForASpecificIndexTest extends AccuweatherAbstractTest {

    @Test
    void getMetadataForASpecificIndex(){

        Metadatum response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .as(Metadatum.class);

        Assertions.assertEquals("Outdoor Concert Forecast", response.getName());
    }
}