-- Создание таблицы Book
CREATE TABLE IF NOT EXISTS book (
    book_id BIGSERIAL PRIMARY KEY,
    year INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы Person
CREATE TABLE IF NOT EXISTS person (
    person_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    family_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (person_id, book_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id) ON DELETE CASCADE
);

-- Создание таблицы BookPersonRoles
CREATE TABLE IF NOT EXISTS book_person_roles (
    person_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (person_id, book_id, role),
    FOREIGN KEY (person_id, book_id) REFERENCES person(person_id, book_id) ON DELETE CASCADE
);

-- Индексы для улучшения производительности
CREATE INDEX IF NOT EXISTS idx_book_year ON book(year);
CREATE INDEX IF NOT EXISTS idx_book_title ON book(title);
CREATE INDEX IF NOT EXISTS idx_person_name ON person(name);
CREATE INDEX IF NOT EXISTS idx_person_family_name ON person(family_name);
CREATE INDEX IF NOT EXISTS idx_book_person_roles_role ON book_person_roles(role);
CREATE INDEX IF NOT EXISTS idx_book_person_roles_person_book ON book_person_roles(person_id, book_id);
CREATE INDEX IF NOT EXISTS idx_person_book ON person(person_id, book_id);