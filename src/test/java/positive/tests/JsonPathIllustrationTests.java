package positive.tests;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.Assert.assertEquals;

public class JsonPathIllustrationTests {

    @Test
    public void shouldReturnItemsBasedOnAuthor() throws IOException {

        String responseString =
                given()
                        .param("author", "krishnamohan777")
                        .param("v", "2")
                        .param("alt", "jsonc").
                when()
                        .get("http://gdata.youtube.com/feeds/api/videos").asString();

        List<String> allItemsReturned = from(responseString).get("data.items");
        List<String> uploadersItems = from(responseString).get("data.items.findAll { i -> i.uploader == 'krishnamohan777'}");

        assertEquals(allItemsReturned.size(), uploadersItems.size());

    }

    @Test
    public void shouldReturnItemsOrderedByRating() throws IOException {

        String responseString =
                given()
                    .param("author", "krishnamohan777")
                    .param("orderby", "rating")
                    .param("v", "2")
                    .param("alt", "jsonc").
                when()
                    .get("http://gdata.youtube.com/feeds/api/videos").asString();

        ArrayList<Double> ratings = from(responseString).get("data.items.rating");
        ratings.removeAll(Collections.singleton(null));

        ArrayList<Double> sortedRatings = new ArrayList<Double>(ratings);
        Collections.sort(sortedRatings);
        Collections.reverse(sortedRatings);

        assertEquals(sortedRatings, ratings);

    }
}
