package com.books.authors.repository;

import com.books.authors.domain.BookPersonRoles;
import com.books.authors.domain.key.BookPersonRolesId;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface BookPersonRolesRepository extends ReactorPageableRepository<BookPersonRoles, BookPersonRolesId> {

    // Правильные методы с префиксом id.
    @NonNull
    Flux<BookPersonRoles> findByIdBookId(@NonNull Long bookId);

    @NonNull
    Flux<BookPersonRoles> findByIdPersonId(@NonNull Long personId);

    @NonNull
    Flux<BookPersonRoles> findByIdRole(@NonNull String role);

    @NonNull
    Mono<BookPersonRoles> findByIdPersonIdAndIdBookIdAndIdRole(@NonNull Long personId,
                                                               @NonNull Long bookId,
                                                               @NonNull String role);

    @NonNull
    Mono<Boolean> existsByIdPersonIdAndIdBookIdAndIdRole(@NonNull Long personId,
                                                         @NonNull Long bookId,
                                                         @NonNull String role);

    @NonNull
    Mono<Long> deleteByIdPersonIdAndIdBookId(@NonNull Long personId, @NonNull Long bookId);

    @NonNull
    Mono<Long> deleteByIdPersonIdAndIdBookIdAndIdRole(@NonNull Long personId,
                                                      @NonNull Long bookId,
                                                      @NonNull String role);

    @NonNull
    Flux<BookPersonRoles> findByIdPersonIdAndIdBookId(@NonNull Long personId, @NonNull Long bookId);

    // Дополнительные методы для сложных запросов
    @NonNull
    Flux<BookPersonRoles> findByIdBookIdAndIdRole(@NonNull Long bookId, @NonNull String role);

    @NonNull
    Flux<BookPersonRoles> findByIdPersonIdAndIdRole(@NonNull Long personId, @NonNull String role);

    @NonNull
    Mono<Long> countByIdPersonId(@NonNull Long personId);

    @NonNull
    Mono<Long> countByIdBookId(@NonNull Long bookId);
}