package org.max.Indices.oneDay;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OneDayOfDailyIndexValuesForAllIndicesTest extends AccuweatherAbstractTest {

    @Test
    void getOneDayValueAllIndicesGroup(){
        List<OneDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/1day/5")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", OneDay.class);

        Assertions.assertEquals(49, response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
    }
}