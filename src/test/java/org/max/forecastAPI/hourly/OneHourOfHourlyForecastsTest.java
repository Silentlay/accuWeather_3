package org.max.forecastAPI.hourly;

import io.restassured.http.Method;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class OneHourOfHourlyForecastsTest extends AccuweatherAbstractTest {

    @Test
    void getOneHourOfHourlyForecastsTest(){
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("location", 294021)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/v1/hourly/1hour/{location}")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L)) // Проверка, что ответ пришел быстрее 2000 мс
                .body("", Matchers.instanceOf(java.util.List.class)) // Проверка, что ответ массив
                .body("size()", Matchers.greaterThan(0)) //Проверка, что массив не пуст
                .body("[0].DateTime", Matchers.not(Matchers.nullValue())) // Проверка, что поле DateTime не null
                .body("[0].DateTime", Matchers.matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*$")); // Проверка формата даты

    }
}
