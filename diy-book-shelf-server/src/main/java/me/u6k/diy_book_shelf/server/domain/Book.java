
package me.u6k.diy_book_shelf.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "original_url")
    private String url;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "event")
    @Enumerated(EnumType.ORDINAL)
    private BookEvent event;

    public Book() {
    }

    public Book(String id, String bookId, String title, String author, String url, Date timestamp, BookEvent event) {
        this.id = id;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.url = url;
        this.timestamp = timestamp;
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public BookEvent getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
