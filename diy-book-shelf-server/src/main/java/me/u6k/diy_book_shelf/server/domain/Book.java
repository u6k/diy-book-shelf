
package me.u6k.diy_book_shelf.server.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "book_id", length = 36, nullable = false)
    private String bookId;

    @Column(name = "title", length = 1000, nullable = false)
    private String title;

    @Column(name = "author", length = 1000, nullable = true)
    private String author;

    @Column(name = "original_url", length = 2000, nullable = true)
    private String url;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "event", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BookEvent event;

    public Book() {
    }

    public Book(UUID id, UUID bookId, String title, String author, String url, Date timestamp, BookEvent event) {
        this.id = (id != null ? id.toString() : null);
        this.bookId = (bookId != null ? bookId.toString() : null);
        this.title = title;
        this.author = author;
        this.url = url;
        this.timestamp = timestamp.getTime();
        this.event = event;
    }

    public UUID getId() {
        return (this.id != null ? UUID.fromString(this.id) : null);
    }

    public UUID getBookId() {
        return (this.bookId != null ? UUID.fromString(this.bookId) : null);
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
        return new Date(this.timestamp);
    }

    public BookEvent getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
