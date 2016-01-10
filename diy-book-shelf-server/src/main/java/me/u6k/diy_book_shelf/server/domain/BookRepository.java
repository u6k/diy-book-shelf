
package me.u6k.diy_book_shelf.server.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    @Override
    List<Book> findAll();

}
