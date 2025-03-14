package org.max.forecastAPI.hourly;

import io.restassured.http.Method;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class HundredAndTwentyHoursOfHourlyForecastsTest extends AccuweatherAbstractTest {

    @Test
    void getHundredAndTwentyHoursOfHourlyForecastsTest(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("location", 294021)
                .when()
                .request(Method.GET, getBaseUrl() + "/forecasts/v1/hourly/120hour/{location}")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L));

    }
}
