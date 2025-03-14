package org.max.Indices.tenDay;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import static io.restassured.RestAssured.given;

public class TenDayAllIndicesTest extends AccuweatherAbstractTest {

    @Test
    void getTenDayIndexValuesForAllTest(){
        Response response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/indices/v1/daily/10day/295212")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response();

        Assertions.assertFalse(response.getBody().asString().isEmpty(), "Ответ пустой при статусе 401");

        TenDay errorResponse = response.as(TenDay.class);

        Assertions.assertEquals("Unauthorized", errorResponse.getCode(), "Поле код не совпадает");

        Assertions.assertEquals("Api Authorization failed", errorResponse.getMessage(), "Сообщение об ошибке не совпадает");

        Assertions.assertTrue(errorResponse.getReference().contains("/indices/v1/daily/10day/295212"),
                "Поле 'Reference' не содержит ожидаемый путь запроса");
    }

}


