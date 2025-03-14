package org.max.location.autocomplete;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.AccuweatherAbstractTest;
import org.max.locations.autoComplete.AutocompleteSearch;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AutoCompleteSearchTest extends AccuweatherAbstractTest {

    @Test
    void getAutoCompleteSearch() {

        List<AutocompleteSearch> response = given()
                .queryParam("apikey", getApiKey())
                .param("q", "Berlin")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AutocompleteSearch.class);

        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("Berlin", response.get(0).getLocalizedName());
    }
}
