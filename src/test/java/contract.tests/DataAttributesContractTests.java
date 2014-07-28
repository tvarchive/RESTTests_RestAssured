package contract.tests;

import contract.Data;
import contract.ResponseContainer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DataAttributesContractTests {

    @Test
    public void shouldHaveDataAttributes() throws IOException {
        String responseString =
                given()
                    .param("q", "sAPUfRycSes")
                    .param("v", "2")
                    .param("alt", "jsonc")
                .when()
                .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseRootObject = mapper.readValue(responseString, ResponseContainer.class);

        Data data = responseRootObject.getData();
        assertNotNull(data.getUpdated());
        assertNotNull(data.getTotalItems());
        assertNotNull(data.getStartIndex());
        assertNotNull(data.getItemsPerPage());

        assertEquals("Size of items did not match", 1, data.getItems().size());

    }
}
