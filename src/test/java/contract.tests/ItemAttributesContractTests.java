package contract.tests;

import contract.AccessControl;
import contract.Item;
import contract.ResponseContainer;
import contract.Restriction;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

public class ItemAttributesContractTests {

    @Test
    public void shouldHaveItemAttributes() throws IOException {
        String responseString =
                given()
                    .param("q", "sAPUfRycSes")
                    .param("v", "2")
                    .param("alt", "jsonc")
                .when()
                .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseRootObject = mapper.readValue(responseString, ResponseContainer.class);

        Item item = responseRootObject.getData().getItems().get(0);

        assertNotNull(item.getId());
        assertNotNull(item.getUploaded());
        assertNotNull(item.getUpdated());
        assertNotNull(item.getUploader());

        assertNotNull(item.getCategory());
        assertNotNull(item.getTitle());
        assertNotNull(item.getDescription());

        assertNotNull(item.getThumbnail());
        assertNotNull(item.getPlayer());
        assertNotNull(item.getContent());
        assertNotNull(item.getDuration());
        assertNotNull(item.getRating());
        assertNotNull(item.getLikeCount());
        assertNotNull(item.getRatingCount());
        assertNotNull(item.getViewCount());
        assertNotNull(item.getFavoriteCount());
        assertNotNull(item.getCommentCount());
        assertNotNull(item.getRestrictions());
        assertNotNull(item.getAccessControl());
    }

    @Test
    public void shouldHaveRestrictionsAttributes() throws IOException {
        String responseString =
                given()
                        .param("q", "sAPUfRycSes")
                        .param("v", "2")
                        .param("alt", "jsonc")
                        .when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseRootObject = mapper.readValue(responseString, ResponseContainer.class);

        Restriction restriction = responseRootObject.getData().getItems().get(0).getRestrictions().get(0);

        assertNotNull(restriction.getType());
        assertNotNull(restriction.getRelationship());
        assertNotNull(restriction.getCountries());

    }

    @Test
    public void shouldHaveAccessControlAttributes() throws IOException {
        String responseString =
                given()
                        .param("q", "sAPUfRycSes")
                        .param("v", "2")
                        .param("alt", "jsonc")
                        .when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ObjectMapper mapper = new ObjectMapper();
        ResponseContainer responseRootObject = mapper.readValue(responseString, ResponseContainer.class);

        AccessControl accessControl = responseRootObject.getData().getItems().get(0).getAccessControl();

        assertNotNull(accessControl.getComment());
        assertNotNull(accessControl.getCommentVote());
        assertNotNull(accessControl.getVideoRespond());
        assertNotNull(accessControl.getRate());
        assertNotNull(accessControl.getEmbed());
        assertNotNull(accessControl.getList());
        assertNotNull(accessControl.getAutoPlay());
        assertNotNull(accessControl.getSyndicate());

    }
}
