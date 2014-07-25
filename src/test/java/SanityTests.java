import com.jayway.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class SanityTests {

    @Test
    public void shouldReturnExactlyOneItem() {
        RestAssured.get("http://gdata.youtube.com/feeds/api/videos?q=sAPUfRycSes&v=2&alt=jsonc")
                .then()
                .assertThat()
                .body("data.totalItems", equalTo(1));
    }
}
