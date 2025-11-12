package com.books.authors;

import com.books.authors.domain.Book;
import com.books.authors.domain.BookPersonRoles;
import com.books.authors.domain.Person;
import com.books.authors.domain.key.BookPersonRolesId;
import com.books.authors.domain.key.PersonId;

import com.books.authors.repository.BookPersonRolesRepository;
import com.books.authors.repository.BookRepository;
import com.books.authors.repository.PersonRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import jakarta.inject.Inject;

@MicronautTest
class IntegrationServiceTest extends PostgresTestPropertiesProvider {

    @Inject
    BookRepository bookRepository;

    @Inject
    PersonRepository personRepository;

    @Inject
    BookPersonRolesRepository rolesRepository;

    @Test
    void testCreateBookWithAuthorAndRole() {
        Long uniquePersonId = System.currentTimeMillis() % 10000;
        Integer uniqueYear = 2000 + (int)(System.currentTimeMillis() % 100);
        Book newBook = new Book(uniqueYear, "Integration Test Book " + System.currentTimeMillis());

        Mono<Book> result = bookRepository.save(newBook)
                .flatMap(savedBook -> {
                    System.out.println("Created book with ID: " + savedBook.getBookId());

                    PersonId authorId = new PersonId(uniquePersonId, savedBook.getBookId());
                    Person author = new Person(authorId, "Integration", "Test");

                    return personRepository.save(author)
                            .flatMap(savedAuthor -> {
                                BookPersonRolesId roleId = new BookPersonRolesId(
                                        savedAuthor.getId().getPersonId(),
                                        savedAuthor.getId().getBookId(),
                                        "AUTHOR"
                                );
                                BookPersonRoles role = new BookPersonRoles();
                                role.setId(roleId);
                                return rolesRepository.save(role);
                            })
                            .thenReturn(savedBook);
                });

        StepVerifier.create(result)
                .expectNextMatches(book ->
                        book.getTitle().contains("Integration Test Book") &&
                                book.getYear().equals(uniqueYear))
                .verifyComplete();
    }
}