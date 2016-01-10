
package me.u6k.diy_book_shelf.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.u6k.diy_book_shelf.server.domain.Book;
import me.u6k.diy_book_shelf.server.domain.BookRepository;

@Service
public class BookService {

    private static final Logger L = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository _repo;

    public List<Book> findAll() {
        L.debug("#findAll");

        List<Book> l = _repo.findAll();

        L.debug("return: " + l);
        return l;
    }

}
