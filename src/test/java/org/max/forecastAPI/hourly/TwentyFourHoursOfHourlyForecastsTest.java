package org.max.forecastAPI.hourly;

import io.restassured.http.Method;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class TwentyFourHoursOfHourlyForecastsTest extends AccuweatherAbstractTest {
    @Test
    void get24HoursForecast_Unauthorized_Returns401(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("location", 294021)
                .when()
                .request(Method.GET, getBaseUrl() + "/forecasts/v1/hourly/24hour/{location}")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L));
    }
}
