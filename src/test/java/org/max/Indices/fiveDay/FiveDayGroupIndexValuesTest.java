package org.max.Indices.fiveDay;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import java.util.List;
import static io.restassured.RestAssured.given;

public class FiveDayGroupIndexValuesTest extends AccuweatherAbstractTest {

    @Test
    void getFiveDaysForAGroup() {

        List<FiveDay> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/5day/52/groups/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", FiveDay.class);

        Assertions.assertEquals(15, response.size());
        Assertions.assertEquals("Fishing Forecast", response.get(0).getName());
    }
}
