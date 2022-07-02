package com.webservicewithapi.webservicefilm.repositories;

import com.webservicewithapi.webservicefilm.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByTitle(String title);
    @Query(
            value = "SELECT * from movies where movies.id_movie not in (select users_movies_favorites.id_movie from users_movies_favorites where users_movies_favorites.id_user = :#{#userId})",
            nativeQuery = true)
    Set<Movie> findMovieWhichNoneFavorites(@Param(value = "userId")Long userId);
}
