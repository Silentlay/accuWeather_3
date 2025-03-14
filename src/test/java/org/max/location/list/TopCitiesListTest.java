package org.max.location.list;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TopCitiesListTest extends AccuweatherAbstractTest {

    @Test
    void getTopCitiesListTest(){
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/topcities/50")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2086L))
                .extract().asString();

        // Выводим полный ответ для отладки
        //System.out.println("Response: " + response);

        // Парсим JSON
        JsonPath jsonPath = new JsonPath(response);

        // Проверяем, что в ответе 50 городов
        List<Object> cities = jsonPath.getList("$");
        Assertions.assertEquals(50, cities.size(), "Expected 50 cities in the response");

        // Проверяем, что первый город - "Dhaka"
        String firstCityName = jsonPath.getString("[0].LocalizedName");
        Assertions.assertEquals("Dhaka", firstCityName, "Expected first city to be Dhaka");
    }

}
