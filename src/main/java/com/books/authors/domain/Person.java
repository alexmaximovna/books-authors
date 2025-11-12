package com.books.authors.domain;

import com.books.authors.domain.key.PersonId;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.sql.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.MapsId;

@MappedEntity("person")
public class Person {
    
    @EmbeddedId
    private PersonId id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "family_name")
    private String familyName;

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
    
    public Person() {}
    
    public Person(PersonId id, String name, String familyName) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
    }


    public PersonId getId() {
        return id;
    }
    
    public void setId(PersonId id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFamilyName() {
        return familyName;
    }
    
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", book=" + book +
                '}';
    }
}