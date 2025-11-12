package com.books.authors.domain;

import io.micronaut.data.annotation.*;
import jakarta.persistence.Column;

@MappedEntity("book")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long bookId;
    
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "title")
    private String title;
    
    public Book() {}
    
    public Book(Integer year, String title) {
        this.year = year;
        this.title = title;
    }
    
    public Long getBookId() {
        return bookId;
    }
    
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }
}