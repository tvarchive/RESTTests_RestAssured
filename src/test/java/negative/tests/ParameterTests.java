package negative.tests;

import contract.ResponseContainer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.equalTo;

public class ParameterTests {

    @Test
    public void shouldReturn0ItemsWhenQParameterIsIncorrect() throws IOException {
        String responseString =
                given()
                        .param("q", "1sAPUfRycSes")
                        .param("v", "2")
                        .param("alt", "jsonc").
                when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseContainer = mapper.readValue(responseString, ResponseContainer.class);
        assertNull(responseContainer.getData().getItems());
        assertEquals(0, responseContainer.getData().getTotalItems());
    }

    @Test
    public void shouldReturnErrorWhenVersionRequestedIsIncorrect() {
        given()
                .param("v", "5")
                .param("alt", "jsonc").
        when()
                .get("http://gdata.youtube.com/feeds/api/videos").
        then()
                .assertThat().body("error.code", equalTo(403)).and()
                .assertThat().body("error.message", equalTo("Version 5 is not supported."));

    }

}
