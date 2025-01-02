package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.utils;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UsersTest {
    private static String userId;
    Faker faker = new Faker();
    String name = faker.name().fullName();
    String job = faker.job().title();

    @Test(priority = 1)
    public void testCreateUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("job", job);
        Response response = given()
                .baseUri(utils.getProperty("base.url"))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all() // Pretty logs request
                .when()
                .post("/api/users")
                .then()
                .log()
                .all() // Pretty logs response
                .assertThat()
                .statusCode(201)
                .extract()
                .response();
        userId = response.jsonPath().getString("id");
        // Log specific fields for debugging
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertNotNull(userId, "User ID should not be null");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        System.out.println("testGetUser :" + userId);
        Response response = given()
                .baseUri(utils.getProperty("base.url"))
                .when()
                .get("/api/users/2")
                .then()
                .log().all() // Pretty logs response
                .extract()
                .response();
        //As When you create a user POST /api/users , the API returns a fake ID and timestamp in the response, but this data is not saved , so I am using here the user id provided from the doc.
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.jsonPath().getString("data.id"));
        Assert.assertEquals(response.jsonPath().getString("data.id"), "2");
    }

    @Test(priority = 3, dependsOnMethods = "testGetUser")
    public void testUpdateUser() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name + " Updated");
        body.put("job", "Shifted career : " + faker.job().title());
        Response response = given()
                .baseUri(utils.getProperty("base.url"))
                .contentType(ContentType.JSON)
                .body(body)
                .log()
                .body()
                .when()
                .put("/api/users/")
                .then()
                .log().all() // Pretty logs response
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), name + " Updated");
    }
}
