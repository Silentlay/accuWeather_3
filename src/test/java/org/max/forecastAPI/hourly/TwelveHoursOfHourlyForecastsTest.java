package org.max.forecastAPI.hourly;

import io.restassured.http.Method;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class TwelveHoursOfHourlyForecastsTest extends AccuweatherAbstractTest {
    @Test
    void getTwelveHoursOfHourlyForecastsTest(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("location", 294021)
                .when()
                .request(Method.GET, getBaseUrl() + "/forecasts/v1/hourly/12hour/{location}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .body("size()", Matchers.greaterThan(0));
    }
}
