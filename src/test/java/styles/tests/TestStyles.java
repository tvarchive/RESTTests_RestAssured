package styles.tests;

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

    @Test
    public void shouldReturnOneItem() {
        get("http://gdata.youtube.com/feeds/api/videos?q=sAPUfRycSes&v=2&alt=jsonc")
            .then()
            .assertThat()
            .body("data.totalItems", equalTo(1));
    }

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

    @Test
    public void shouldReturnOneItemToo() {
        String responseString =
                given()
                    .param("q", "sAPUfRycSes")
                    .param("v", "2")
                    .param("alt", "jsonc").
                when()
                    .get("http://gdata.youtube.com/feeds/api/videos").asString();

        int totalItems = from(responseString).get("data.totalItems");

        assertEquals(1, totalItems);

    }

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
