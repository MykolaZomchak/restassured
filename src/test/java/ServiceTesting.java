import io.restassured.http.ContentType;
import model.Photo;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static io.restassured.http.Method.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ServiceTesting {

    @Test
    public static void schemaTest(){
        given().headers("Accept", "application/json", "Content Type", "application/json").
        when().request(GET, "https://jsonplaceholder.typicode.com/users/1").
        then().body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    public static void groovyParsing(){
        given().contentType(JSON).accept(JSON).
        when().get("https://jsonplaceholder.typicode.com/users").
        then().body("findAll{ it.id < 6 }.username", hasItems("Bret", "Kamren")).
               assertThat().statusCode(200);
    }

    @Test
    public static void timeTest(){
        Photo body = new Photo(1,1, "accusamus beatae ad facilis cum similique qui sunt",
                            "http://placehold.it/600/92c952","http://placehold.it/150/92c952");

        long timeInMs = put("https://jsonplaceholder.typicode.com/users/1").time();
        long timeInSeconds = patch("https://jsonplaceholder.typicode.com/users/1").timeIn(SECONDS);

        given().auth().basic("MyName", "MyPassword").contentType(JSON).body(body).log().all().
        when().delete("https://jsonplaceholder.typicode.com/photos.").then().time(lessThan(2000L)).log().all();
    }

    @Test
    public static void deserializationTest(){
        Photo expected = new Photo(1,1, "accusamus beatae ad facilis cum similique qui sunt",
                "http://placehold.it/600/92c952","http://placehold.it/150/92c952");

        Photo actual = get("https://jsonplaceholder.typicode.com/photos/1").as(Photo.class);

        Assert.assertEquals(expected, actual);
    }

}
