package org.max.Indices.metadata;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ListOfDailyIndicesTest extends AccuweatherAbstractTest {
    @Test
    void getListOfDailyIndices(){
        List<Metadatum> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Metadatum.class);

        Assertions.assertEquals(118, response.size());
        Assertions.assertEquals("Air Conditioning Index", response.get(0).getName());
    }
}
