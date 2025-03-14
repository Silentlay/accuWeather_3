package org.max.location.textsearch;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GETCitySearchTest {
    @Test
    public void testCitySearch() {
        String apiKey = "ijar3DYGLTWjxxF7Jni6v6KgpL8E3Qq1"; //  API-ключ

        RestAssured.baseURI = "http://dataservice.accuweather.com";

        // Выполнение GET запроса
        Response response = given()
                .queryParam("apikey", apiKey)
                .queryParam("q", "Missouri")
                .when()
                .get("/locations/v1/cities/search")
                .then()
                .statusCode(200) // Проверка на успешный статус код 200
                .body("size()", greaterThan(0)) // Убедиться, что ответ не пуст
                .body("LocalizedName", hasItem("Missouri")) // Проверка на наличие "Missouri" в поле LocalizedName
                .body("Country.EnglishName", hasItem("Canada")) // Проверка на наличие страны "Canada"
                .body("Region.EnglishName", hasItem("North America")) // Проверка на регион "North America"
                .extract().response();

        // Вывод ответа для дополнительной проверки
        //System.out.println(response.getBody().asString());
    }
}