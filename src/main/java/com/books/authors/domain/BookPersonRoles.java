package com.books.authors.domain;

import com.books.authors.domain.key.BookPersonRolesId;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.sql.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.MapsId;

import java.time.LocalDateTime;

@MappedEntity("book_person_roles")
public class BookPersonRoles {

    @EmbeddedId
    private BookPersonRolesId id;

    @DateCreated
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @DateUpdated
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    @MapsId("personId")
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    // добавить(при удалении @MapsId, @JoinColumn) @MappedProperty("person_id")
    // @MappedProperty("person_id")
    private Person person;

    @Transient
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    @MapsId("book_id")
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    // добавить(при удалении @MapsId, @JoinColumn) @MappedProperty("book_id")
    private Book book;

    public BookPersonRoles() {}

    public BookPersonRoles(BookPersonRolesId id, Person person, Book book) {
        this.id = id;
        this.person = person;
        this.book = book;
    }

    public BookPersonRoles(Long personId, Long bookId, String role, Person person, Book book) {
        this.id = new BookPersonRolesId(personId, bookId, role);
        this.person = person;
        this.book = book;
    }

    public BookPersonRolesId getId() {
        return id;
    }

    public void setId(BookPersonRolesId id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BookPersonRoles{" +
                "id=" + id +
                '}';
    }
}