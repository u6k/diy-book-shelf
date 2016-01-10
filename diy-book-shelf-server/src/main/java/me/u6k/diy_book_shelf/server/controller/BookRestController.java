
package me.u6k.diy_book_shelf.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.u6k.diy_book_shelf.server.domain.Book;
import me.u6k.diy_book_shelf.server.service.BookService;

@RestController
@RequestMapping(value = "books")
public class BookRestController {

    private static final Logger L = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private BookService _s;

    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> findAll() {
        L.debug("#findAll");

        List<Book> bookList = _s.findAll();

        List<Map<String, Object>> l = new ArrayList<>();
        for (Book book : bookList) {
            Map<String, Object> b = new HashMap<>();
            b.put("book_id", book.getBookId().toString());
            b.put("title", book.getTitle());
            if (book.getAuthor() != null) {
                b.put("author", book.getAuthor());
            }
            if (book.getUrl() != null) {
                b.put("url", book.getUrl());
            }
            b.put("timestamp", book.getTimestamp().getTime());

            l.add(b);
        }

        L.debug("return: " + l);
        return l;
    }

}
