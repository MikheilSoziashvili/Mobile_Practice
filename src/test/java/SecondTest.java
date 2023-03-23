import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@WireMockTest(proxyMode = true)
public class SecondTest {

    @Test
    public void solution() {
        List<ExtractableResponse<Response>> responses = IntStream.range(0, 20)
                .mapToObj(i -> RestAssured.given()
                        .relaxedHTTPSValidation()
                        .when()
                        .baseUri("https://www.boredapi.com")
                        .get("/api/activity")
                        .then()
                        .extract())
                .toList();

        responses.stream()
                .filter(response -> Double.parseDouble(response.jsonPath().get("price").toString()) > 0.0)
                .sorted(Comparator.comparing(response -> response.jsonPath().getDouble("accessibility")))
                .map(it -> it.body().asString())
                .forEach(System.out::println);
    }

//    @Test
//    public void secondSolution() {
//        stubFor(get("/api/activity")
//                .withHost(equalTo("www.boredapi.com"))
//                .willReturn(status(200).withBodyFile("api_response.json")));
//
//        Response responses = RestAssured.given()
//                .relaxedHTTPSValidation()
//                .when()
//                .baseUri("https://www.boredapi.com")
//                .get("/api/activity");
//
////        System.out.println(responses.body().asString());
//
//        JsonPath jsonPath = responses.body().jsonPath();
//
//        List<Map<String, Object>> filteredAndSorted = jsonPath.get("findAll { it -> it.price > 0 }.sort { it.accessibility }");
//
//        for (Map<String, Object> item : filteredAndSorted) {
//            System.out.println(item);
//        }
//    }
}