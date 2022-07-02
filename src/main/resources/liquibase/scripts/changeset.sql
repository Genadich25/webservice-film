-- liquibase formatted sql

-- changeset pavelignatev:1
CREATE TABLE users (
    id_user BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    name TEXT

);

-- changeset pavelignatev:2
CREATE TABLE movies (
    id_movie BIGSERIAL NOT NULL UNIQUE PRIMARY KEY,
    poster_path TEXT UNIQUE,
    title TEXT UNIQUE
);

-- changeset pavelignatev:3
CREATE TABLE users_movies_favorites (
    id_user BIGSERIAL NOT NULL,
    id_movie BIGSERIAL NOT NULL,
    PRIMARY KEY (id_user, id_movie),
    CONSTRAINT users FOREIGN KEY(id_user) REFERENCES users(id_user),
    CONSTRAINT movies FOREIGN KEY(id_movie) REFERENCES movies(id_movie)
);
