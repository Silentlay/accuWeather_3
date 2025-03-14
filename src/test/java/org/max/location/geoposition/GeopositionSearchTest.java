package org.max.location.geoposition;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.path.json.JsonPath;
import org.max.AccuweatherAbstractTest;

public class GeopositionSearchTest extends AccuweatherAbstractTest {

    @Test
    public void testGeoPositionForSaintPetersburg() {
        // Проверка, что apiKey и baseUrl были правильно загружены
        String apiKey = getApiKey();
        String baseUrl = getBaseUrl();

        // Выполняем запрос с использованием RestAssured
        String response = given()
                .queryParam("apikey", apiKey)
                .queryParam("q", "59.932,30.327") // Координаты Санкт-Петербурга
                .when()
                .get(baseUrl + "/locations/v1/cities/geoposition/search")
                .then()
                .statusCode(200) // Проверка кода 200
                .extract().asString();

        // Выводим полный ответ для отладки
        //System.out.println("Response: " + response);

        // Парсим JSON и получаем значение поля 'LocalizedName'
        JsonPath jsonPath = new JsonPath(response);
        String localizedName = jsonPath.getString("LocalizedName");

        // Пример проверки
        assertEquals("No 78", localizedName);
    }

    @Test
    public void testGeoPositionDetailsForSaintPetersburg() {
        // Проверка, что apiKey и baseUrl были правильно загружены
        String apiKey = getApiKey();
        String baseUrl = getBaseUrl();

        // Выполняем запрос с использованием RestAssured
        String response = given()
                .queryParam("apikey", apiKey)
                .queryParam("q", "59.932,30.327") // Координаты Санкт-Петербурга
                .when()
                .get(baseUrl + "/locations/v1/cities/geoposition/search")
                .then()
                .statusCode(200) // Проверка кода 200
                .extract().asString();

        // Выводим полный ответ для отладки
        //System.out.println("Response: " + response);

        // Парсим JSON
        JsonPath jsonPath = new JsonPath(response);

        // Проверка названия города
        String administrativeAreaLocalizedName = jsonPath.getString("AdministrativeArea.LocalizedName");
        assertEquals("Saint Petersburg", administrativeAreaLocalizedName);

        String administrativeAreaEnglishName = jsonPath.getString("AdministrativeArea.EnglishName");
        assertEquals("Saint Petersburg", administrativeAreaEnglishName);

        // Проверка координат
        double latitude = jsonPath.getDouble("GeoPosition.Latitude");
        double longitude = jsonPath.getDouble("GeoPosition.Longitude");
        assertEquals(59.932, latitude, 0.1);
        assertEquals(30.327, longitude, 0.1);

        // Проверка региона
        String regionEnglishName = jsonPath.getString("Region.EnglishName");
        assertEquals("Asia", regionEnglishName);

        // Проверка страны
        String countryEnglishName = jsonPath.getString("Country.EnglishName");
        assertEquals("Russia", countryEnglishName);

        // Проверка часового пояса
        String timeZoneName = jsonPath.getString("TimeZone.Name");
        assertEquals("Europe/Moscow", timeZoneName);

        // Проверка родительского города
        String parentCityEnglishName = jsonPath.getString("ParentCity.EnglishName");
        assertEquals("Saint Petersburg", parentCityEnglishName);
    }
}


