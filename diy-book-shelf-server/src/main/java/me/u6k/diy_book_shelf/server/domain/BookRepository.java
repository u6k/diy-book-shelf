
package me.u6k.diy_book_shelf.server.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, String> {

    @Override
    @Query
    List<Book> findAll();

}
