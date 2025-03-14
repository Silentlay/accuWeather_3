package org.max.Indices.fifteenDay;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class DailyIndexValuesForAllIndicesTest extends AccuweatherAbstractTest {

    @Test
    void get15DaysOfDailyIndexValuesForAllIndices(){
        Response response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/15day/295212")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response();

        Assertions.assertFalse(response.getBody().asString().isEmpty(), "Ответ пустой при статусе 401");

        FifteenDay errorResponse = response.as(FifteenDay.class);

        Assertions.assertEquals("Unauthorized", errorResponse.getCode(), "Поле код не совпадает");

        Assertions.assertEquals("Api Authorization failed", errorResponse.getMessage(), "Сообщение об ошибке не совпадает");

        Assertions.assertTrue(errorResponse.getReference().contains("/indices/v1/daily/15day/295212"),
                "Поле 'Reference' не содержит ожидаемый путь запроса");
    }
}
