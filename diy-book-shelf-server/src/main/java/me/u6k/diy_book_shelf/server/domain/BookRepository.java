
package me.u6k.diy_book_shelf.server.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, String> {

    @Override
    @Query(value = "select b from Book b where not exists ( select 1 from Book b2 where b.bookId = b2.bookId and b.timestamp < b2.timestamp ) and b.event in (0, 1) order by b.timestamp desc")
    List<Book> findAll();

}
