
package me.u6k.diy_book_shelf.server.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.u6k.diy_book_shelf.server.App;
import me.u6k.diy_book_shelf.server.domain.Book;
import me.u6k.diy_book_shelf.server.domain.BookEvent;
import me.u6k.diy_book_shelf.server.domain.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class BookServiceTest {

    @Autowired
    private BookService _s;

    @Autowired
    private BookRepository _bookRepo;

    @Before
    public void setup() {
        _bookRepo.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        // 新規登録(最小限)
        String bookId1 = UUID.randomUUID().toString();
        Book book = new Book(UUID.randomUUID().toString(), bookId1, "テスト書籍1", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 新規登録(全項目)
        String bookId2 = UUID.randomUUID().toString();
        book = new Book(UUID.randomUUID().toString(), bookId2, "テスト書籍2", "テスト　著者", "http://example.com/test2", new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 更新(最小限→全項目)
        String bookId3 = UUID.randomUUID().toString();
        book = new Book(UUID.randomUUID().toString(), bookId3, "テスト書籍3", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID().toString(), bookId3, "テスト書籍3-1", "テスト　著者", "http://example.com/test3", new Date(), BookEvent.UPDATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 更新(全項目→最小限)
        String bookId4 = UUID.randomUUID().toString();
        book = new Book(UUID.randomUUID().toString(), bookId4, "テスト書籍4", "テスト　著者", "http://example.com/test4", new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID().toString(), bookId4, "テスト書籍4-1", null, null, new Date(), BookEvent.UPDATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        // 論理削除(新規登録→論理削除)
        String bookId5 = UUID.randomUUID().toString();
        book = new Book(UUID.randomUUID().toString(), bookId5, "テスト書籍5", null, null, new Date(), BookEvent.CREATE);
        _bookRepo.save(book);

        Thread.sleep(100);

        book = new Book(UUID.randomUUID().toString(), bookId5, book.getTitle(), null, null, new Date(), BookEvent.DELETE);
        _bookRepo.save(book);

        // 書籍を全検索する。
        List<Book> l = _s.findAll();

        // 検索結果を検証する。
        assertThat(l.size(), is(4));

        book = l.get(0);
        assertThat(book.getBookId(), is(bookId4));
        assertThat(book.getTitle(), is("テスト書籍4-1"));
        assertThat(book.getAuthor(), is(nullValue()));
        assertThat(book.getUrl(), is(nullValue()));
        assertThat(book.getTimestamp().getTime(), greaterThan(0L));

        book = l.get(1);
        assertThat(book.getBookId(), is(bookId3));
        assertThat(book.getTitle(), is("テスト書籍3-1"));
        assertThat(book.getAuthor(), is("テスト　著者"));
        assertThat(book.getUrl(), is("http://example.com/test3"));
        assertThat(book.getTimestamp().getTime(), greaterThan(0L));

        book = l.get(2);
        assertThat(book.getBookId(), is(bookId2));
        assertThat(book.getTitle(), is("テスト書籍2"));
        assertThat(book.getAuthor(), is("テスト　著者"));
        assertThat(book.getUrl(), is("http://example.com/test2"));
        assertThat(book.getTimestamp().getTime(), greaterThan(0L));

        book = l.get(3);
        assertThat(book.getBookId(), is(bookId1));
        assertThat(book.getTitle(), is("テスト書籍1"));
        assertThat(book.getAuthor(), is(nullValue()));
        assertThat(book.getUrl(), is(nullValue()));
        assertThat(book.getTimestamp().getTime(), greaterThan(0L));
    }

    @Test
    public void findAll_0件() {
        List<Book> l = _s.findAll();

        assertThat(l.size(), is(0));
    }

    @Test
    public void add() {
        String title = "test"; // TODO 手頃なテストデータを確保する。

        String bookId = _s.add(title, null, null);
        assertThat(bookId.length(), is(36));

        Book book = _s.findByBookId(bookId);

        assertThat(book.getId().length(), is(36));
        assertThat(book.getBookId().length(), is(36));
        assertThat(book.getTitle(), is("test"));
        assertThat(book.getAuthor(), is(nullValue()));
        assertThat(book.getUrl(), is(nullValue()));
    }

}
