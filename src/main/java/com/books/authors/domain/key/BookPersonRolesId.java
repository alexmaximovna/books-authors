package com.books.authors.domain.key;

import io.micronaut.core.annotation.Introspected;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Introspected
public class BookPersonRolesId implements Serializable {
    
    @Column(name = "person_id")
    private Long personId;
    
    @Column(name = "book_id")
    private Long bookId;
    
    @Column(name = "role")
    private String role;
    
    public BookPersonRolesId() {}
    
    public BookPersonRolesId(Long personId, Long bookId, String role) {
        this.personId = personId;
        this.bookId = bookId;
        this.role = role;
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
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPersonRolesId that = (BookPersonRolesId) o;
        return Objects.equals(personId, that.personId) && 
               Objects.equals(bookId, that.bookId) && 
               Objects.equals(role, that.role);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(personId, bookId, role);
    }
    
    @Override
    public String toString() {
        return "BookPersonRolesId{" +
                "personId=" + personId +
                ", bookId=" + bookId +
                ", role='" + role + '\'' +
                '}';
    }
}