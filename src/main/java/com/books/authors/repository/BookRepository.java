package com.books.authors.repository;

import com.books.authors.domain.Book;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface BookRepository extends ReactorPageableRepository<Book, Long> {
    
    @NonNull
    Mono<Book> findByTitle(@NonNull String title);
    
    @NonNull
    Flux<Book> findByYear(@NonNull Integer year);
    
    @NonNull
    Flux<Book> findByYearBetween(@NonNull Integer startYear, @NonNull Integer endYear);
    
    @NonNull
    Mono<Long> countByYear(@NonNull Integer year);
}