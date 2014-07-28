package boundary.tests;

import com.jayway.restassured.path.json.JsonPath;
import contract.ResponseContainer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.equalTo;

public class ParameterBoundaryTests {

    @Test
    public void shouldReturn0ItemsWhenMaxResultsIsSpecifiedAs0() throws IOException {
        String responseString =
                given()
                        .param("max-results", "0")
                        .param("v", "2")
                        .param("alt", "jsonc").
                when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseContainer = mapper.readValue(responseString, ResponseContainer.class);
        assertNull(responseContainer.getData().getItems());
    }

    @Test
    public void shouldReturn50ItemsWhenMaxResultsIsSpecifiedAs50() {
        String responseString =
                given()
                        .param("max-results", "50")
                        .param("v", "2")
                        .param("alt", "jsonc").
                when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        JsonPath jp = new JsonPath(responseString);
        assertEquals(50, jp.getList("data.items").size());
    }

    @Test
    public void shouldReturnErrorWhenItemsRequestedis51() {
        given()
                .param("max-results", "51")
                .param("v", "2")
                .param("alt", "jsonc").
        when()
                .get("http://gdata.youtube.com/feeds/api/videos").
        then()
                .assertThat().body("error.code", equalTo(400)).and()
                .assertThat().body("error.message", equalTo("Max-results value is too high. Only up to 50 results can be returned per query."));

    }

    @Test
    public void shouldReturnErrorWhenItemsRequestedIsMinus1() {
        given()
                .param("max-results", "-1")
                .param("v", "2")
                .param("alt", "jsonc").
        when()
                .get("http://gdata.youtube.com/feeds/api/videos").
        then()
                .assertThat().body("error.code", equalTo(400)).and()
                .assertThat().body("error.message", equalTo("Invalid max-results"));

    }


}
