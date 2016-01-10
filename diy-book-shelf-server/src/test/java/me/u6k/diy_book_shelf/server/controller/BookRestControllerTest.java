
package me.u6k.diy_book_shelf.server.controller;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import me.u6k.diy_book_shelf.server.App;
import me.u6k.diy_book_shelf.server.domain.Book;
import me.u6k.diy_book_shelf.server.domain.BookEvent;
import me.u6k.diy_book_shelf.server.domain.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.post:0" })
public class BookRestControllerTest {

    @Autowired
    private BookRepository _bookRepo;

    @Value("${local.server.port:0}")
    private int _port;

    @Before
    public void setup() {
        RestAssured.port = _port;

        _bookRepo.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        // 新規登録(最小限)
        UUID bookId1 = UUID.randomUUID();
        Book book = new Book(UUID.randomUUID(), bookId1, "テスト書籍1", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 新規登録(全項目)
        UUID bookId2 = UUID.randomUUID();
        book = new Book(UUID.randomUUID(), bookId2, "テスト書籍2", "テスト　著者", "http://example.com/test2", new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 更新(最小限→全項目)
        UUID bookId3 = UUID.randomUUID();
        book = new Book(UUID.randomUUID(), bookId3, "テスト書籍3", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID(), bookId3, "テスト書籍3-1", "テスト　著者", "http://example.com/test3", new Date(), BookEvent.UPDATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 更新(全項目→最小限)
        UUID bookId4 = UUID.randomUUID();
        book = new Book(UUID.randomUUID(), bookId4, "テスト書籍4", "テスト　著者", "http://example.com/test4", new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID(), bookId4, "テスト書籍4-1", null, null, new Date(), BookEvent.UPDATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 論理削除(新規登録→論理削除)
        UUID bookId5 = UUID.randomUUID();
        book = new Book(UUID.randomUUID(), bookId5, "テスト書籍5", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID(), bookId5, book.getTitle(), null, null, new Date(), BookEvent.DELETE);
        _bookRepo.save(book);

        // 書籍を全検索して、結果を検証する。
        given() //
                .contentType(ContentType.JSON) //
                .config(getUTF8Config()) //
                .when() //
                .get("/books") //
                .then() //
                .statusCode(HttpStatus.OK.value()) //
                .body("book_id[0]", is(bookId4.toString())) //
                .body("title[0]", is("テスト書籍4-1")) //
                .body("author[0]", is(nullValue())) //
                .body("url[0]", is(nullValue())) //
                .body("timestamp[0]", greaterThan(0L)) //
                .body("book_id[1]", is(bookId3.toString())) //
                .body("title[1]", is("テスト書籍3-1")) //
                .body("author[1]", is("テスト　著者")) //
                .body("url[1]", is("http://example.com/test3")) //
                .body("timestamp[1]", greaterThan(0L)) //
                .body("book_id[2]", is(bookId2.toString())) //
                .body("title[2]", is("テスト書籍2")) //
                .body("author[2]", is("テスト　著者")) //
                .body("url[2]", is("http://example.com/test2")) //
                .body("timestamp[2]", greaterThan(0L)) //
                .body("book_id[3]", is(bookId1.toString())) //
                .body("title[3]", is("テスト書籍1")) //
                .body("author[3]", is(nullValue())) //
                .body("url[3]", is(nullValue())) //
                .body("timestamp[3]", greaterThan(0L)); //
    }

    @Test
    public void findAll_0件() {
        Response resp = given() //
                .contentType(ContentType.JSON) //
                .config(getUTF8Config()) //
                .when() //
                .get("/books");

        assertThat(resp.getStatusCode(), is(HttpStatus.OK.value()));
        assertThat(resp.asString(), is("[]"));
    }

    private RestAssuredConfig getUTF8Config() {
        return new RestAssuredConfig().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
    }

}
