package org.max.Indices.oneDay;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OneDayOfDailyIndexValuesForAGroupOfIndicesTest extends AccuweatherAbstractTest {
    @Test
    void getOneDayValuesGroup(){
        Response response = given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", 295212)
                .when()
                .get(getBaseUrl()+"/indices/{version}/daily/1day/{location}/groups/1")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response();

        // Проверка, что ответ не пустой
        String responseBody = response.getBody().asString();
        Assertions.assertFalse(responseBody.isEmpty(), "Ответ пустой");

        // Проверка, что это массив
        Assertions.assertTrue(responseBody.startsWith("["), "Ожидался массив, но пришло что-то другое");

        //Десериализация
        List<OneDay> indices = response.jsonPath().getList(".", OneDay.class);
        Assertions.assertFalse(indices.isEmpty(), "Список индексов пуст");

        //Проверка, что у каждого объекта есть поле "Name"
        for (OneDay index : indices){
            Assertions.assertNotNull(index.getName(), "Поле 'Name' отсутствует у одного из индексов");
        }

        Assertions.assertEquals(49, indices.size());
        Assertions.assertEquals("Flight Delays", indices.get(0).getName());
    }
}
