package positive.tests;

import contract.Item;
import contract.ResponseContainer;
import contract.Restriction;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class DataValidatorTests {

    static ResponseContainer responseContainer;

    @BeforeClass
    public static void beforeClass() throws IOException {
        String responseString =
                given()
                        .param("q", "sAPUfRycSes")
                        .param("v", "2")
                        .param("alt", "jsonc").
                when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        responseContainer = mapper.readValue(responseString, ResponseContainer.class);
    }

    @Test
    public void assertDataAttributes() {
        assertEquals(1, responseContainer.getData().getTotalItems());
        assertEquals(1, responseContainer.getData().getStartIndex());
        assertEquals(25, responseContainer.getData().getItemsPerPage());
    }

    @Test
    public void assertItemAttributes() {
        Item item = responseContainer.getData().getItems().get(0);
        assertEquals("sAPUfRycSes", item.getId());
        assertEquals("krishnamohan777", item.getUploader());
        assertEquals("Entertainment", item.getCategory());
        assertEquals("Maa thujhe salaam song on the stage by A R Rehman", item.getTitle());
        assertEquals(288, item.getDuration());
    }

    @Test
    public void assertRestrictionAttributes() {
        Restriction restriction = responseContainer.getData().getItems().get(0).getRestrictions().get(0);
        assertEquals("country", restriction.getType());
        assertEquals("deny", restriction.getRelationship());
        assertEquals("DE", restriction.getCountries());
    }
}
