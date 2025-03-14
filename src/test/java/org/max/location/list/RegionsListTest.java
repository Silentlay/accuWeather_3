package org.max.location.list;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.list.Regions;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RegionsListTest extends AccuweatherAbstractTest {
    @Test
    void getRegionList() {

        List<Regions> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/regions")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2500L))
                .extract()
                .body().jsonPath().getList(".", Regions.class);

        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("Africa", response.get(0).getLocalizedName());
    }
}
