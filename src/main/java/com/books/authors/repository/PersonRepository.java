package com.books.authors.repository;

import com.books.authors.domain.Person;
import com.books.authors.domain.key.PersonId;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface PersonRepository extends ReactorPageableRepository<Person, PersonId> {

    @NonNull
    Flux<Person> findByIdBookId(@NonNull Long bookId);

    @NonNull
    Flux<Person> findByNameAndFamilyName(@NonNull String name, @NonNull String familyName);

    @NonNull
    Mono<Person> findByIdPersonIdAndIdBookId(@NonNull Long personId, @NonNull Long bookId);

    @NonNull
    Flux<Person> findByIdBookIdIn(@NonNull Iterable<Long> bookIds);

    @NonNull
    Mono<Long> countByIdBookId(@NonNull Long bookId);

    @NonNull
    Mono<Boolean> existsByIdPersonIdAndIdBookId(@NonNull Long personId, @NonNull Long bookId);

    @NonNull
    Mono<Long> deleteByIdPersonIdAndIdBookId(@NonNull Long personId, @NonNull Long bookId);

    // Дополнительные методы
    @NonNull
    Flux<Person> findByNameContainingIgnoreCase(@NonNull String name);

    @NonNull
    Flux<Person> findByFamilyNameContainingIgnoreCase(@NonNull String familyName);
}