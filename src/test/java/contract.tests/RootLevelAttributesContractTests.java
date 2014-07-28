package contract.tests;

import contract.ResponseContainer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static junit.framework.Assert.assertNotNull;

public class RootLevelAttributesContractTests {

    @Test
    public void shouldHaveRootLevelAttributes() throws IOException {
        String responseString =
                given()
                    .param("q", "sAPUfRycSes")
                    .param("v", "2")
                    .param("alt", "jsonc")
                .when()
                .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseRootObject = mapper.readValue(responseString, ResponseContainer.class);

        assertNotNull(responseRootObject.getApiVersion());
        assertNotNull(responseRootObject.getData());
    }
}
