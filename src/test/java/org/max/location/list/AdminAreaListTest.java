package org.max.location.list;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.list.AdminArea;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AdminAreaListTest extends AccuweatherAbstractTest {

    @Test
    void getListAdminArea() {

        List<AdminArea> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/adminareas/ru")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AdminArea.class);

        Assertions.assertEquals(83, response.size());
        Assertions.assertEquals("Adygeya", response.get(0).getLocalizedName());
    }
}
