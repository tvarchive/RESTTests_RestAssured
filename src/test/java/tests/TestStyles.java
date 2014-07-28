package tests;

import contract.ResponseContainer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestStyles {

    // 1. Simple JSON Validation
    @Test
    public void shouldReturnOneItem() {
        get("http://gdata.youtube.com/feeds/api/videos?q=sAPUfRycSes&v=2&alt=jsonc")
            .then()
            .assertThat()
            .body("data.totalItems", equalTo(1));
    }

    // 2. Using parameters and BDD style for better readability
    @Test
    public void shouldAlsoReturnOneItem() {
        given()
                .param("q", "sAPUfRycSes")
                .param("v", "2")
                .param("alt", "jsonc").
        when()
                .get("http://gdata.youtube.com/feeds/api/videos").
        then()
                .body("data.totalItems", equalTo(1));
    }

    // 3. Getting response as string and parsing it using JsonPath
    @Test
    public void shouldReturnOneItemToo() {
        String responseString = get("http://gdata.youtube.com/feeds/api/videos?q=sAPUfRycSes&v=2&alt=jsonc").asString();

        int totalItems = from(responseString).get("data.totalItems");

        assertEquals(1, totalItems);

    }

    // 4. Converting Json response string as an object, then performing assertion
    @Test
    public void shouldReturnTwoItems() throws IOException {
        String responseString =
                given()
                    .param("q", "rehman")
                    .param("v", "2")
                    .param("max-results", "2")
                    .param("alt", "jsonc").
                when()
                    .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseContainer = mapper.readValue(responseString, ResponseContainer.class);

        assertEquals(2, responseContainer.getData().getItems().size());
    }




}
