package com.books.authors.domain.key;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.MappedProperty;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Introspected
public class PersonId implements Serializable {
    
    @MappedProperty("person_id")
    private Long personId;
    
    @MappedProperty("book_id")
    private Long bookId;
    
    public PersonId() {}
    
    public PersonId(Long personId, Long bookId) {
        this.personId = personId;
        this.bookId = bookId;
    }
    
    public Long getPersonId() {
        return personId;
    }
    
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    
    public Long getBookId() {
        return bookId;
    }
    
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonId personId1 = (PersonId) o;
        return Objects.equals(personId, personId1.personId) && 
               Objects.equals(bookId, personId1.bookId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(personId, bookId);
    }
    
    @Override
    public String toString() {
        return "PersonId{" +
                "personId=" + personId +
                ", bookId=" + bookId +
                '}';
    }
}