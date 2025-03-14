package org.max.location.ipaddress;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.ipAddress.IPAddress;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class IpAddressSearchTest extends AccuweatherAbstractTest {

    @Test
    void getIpAddressSearchTest() {
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/ipaddress")
                .then()
                .statusCode(200) // Проверка, что статус код 200
                .time(Matchers.lessThan(2000L)) // Проверка, что ответ пришел быстрее 2000 мс
                .extract().asString();

        JsonPath jsonPath = new JsonPath(response);

        // Проверка, что в ответе есть ключ "LocalizedName"
        assertTrue(jsonPath.getMap("$").containsKey("LocalizedName"), "Ответ не содержит ключ 'LocalizedName'");

        // Проверка, что название города не пустое
        String localizedName = jsonPath.getString("LocalizedName");
        assertNotNull(localizedName, "Название города не должно быть null");
        assertFalse(localizedName.isEmpty(), "Название города не должно быть пустым");
    }

    @Test
    void testGetCityByIp() {
        Response response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/ipaddress")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .response();

        IPAddress ipAddressResponse = response.as(IPAddress.class);

        assertNotNull(ipAddressResponse, "Ответ не должен быть null");
        assertNotNull(ipAddressResponse.getLocalizedName(), "LocalizedName не должен быть null");
        assertFalse(ipAddressResponse.getLocalizedName().isEmpty(), "LocalizedName не должен быть пустым");
    }
}

