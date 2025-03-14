package org.max.forecastAPI.daily;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class TenDaysTest extends AccuweatherAbstractTest {

    @Test
    void getTenDayReturn_401() {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", 290396)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/10day/{location}")
                .then()
                .statusCode(401);
    }
}
