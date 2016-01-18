
package me.u6k.diy_book_shelf.server.controller.env;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import me.u6k.diy_book_shelf.server.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.post:0" })
public class ActuatorTest {

    @Value("${local.server.port:0}")
    private int _port;

    @Before
    public void setup() {
        RestAssured.port = _port;
    }

    @Test
    public void info() {
        given() //
                .get("/env/info") //
                .then() //
                .statusCode(HttpStatus.OK.value()) //
                .body("name", is("diy-book-shelf-server")) //
                .body("version", is("0.1.0-SNAPSHOT"));
    }

}
