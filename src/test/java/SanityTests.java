import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SanityTests {

    // Simple JSON Validation
    @Test
    public void shouldReturnExactlyOneItem() {
        get("http://gdata.youtube.com/feeds/api/videos?q=sAPUfRycSes&v=2&alt=jsonc")
            .then()
            .assertThat()
            .body("data.totalItems", equalTo(1));
    }

    // Using parameters and BDD style for better readability
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



}
