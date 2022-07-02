package com.webservicewithapi.webservicefilm.controllers;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.exceptions.BadRequestException;
import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.services.FavoritesServiceImpl;
import com.webservicewithapi.webservicefilm.services.Movie.MovieSearchServiceInMemoryImpl;
import com.webservicewithapi.webservicefilm.services.Movie.MovieSearchServiceSQLImpl;
import com.webservicewithapi.webservicefilm.services.Movie.MovieServiceImpl;
import com.webservicewithapi.webservicefilm.services.interfaces.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    private final MovieServiceImpl movieService;
    private final FavoritesServiceImpl favoritesService;
    private final MovieSearchServiceSQLImpl searchServiceSQL;
    private final MovieSearchServiceInMemoryImpl searchServiceInMemory;

    public MovieController(MovieServiceImpl movieService,
                           FavoritesServiceImpl favoritesService,
                           MovieSearchServiceSQLImpl searchServiceSQL,
                           MovieSearchServiceInMemoryImpl searchServiceInMemory) {
        this.movieService = movieService;
        this.favoritesService = favoritesService;
        this.searchServiceSQL = searchServiceSQL;
        this.searchServiceInMemory = searchServiceInMemory;
    }

    @GetMapping(value = "/get-movie-by-pages")
    public ResponseEntity<List<Movie>> getMovieByPages(@RequestParam(required = true) Integer page,
                                                       @RequestParam(required = false, defaultValue = "15") Integer size){
        List<Movie> movies = movieService.getMovieByPages(page, size);
        return ResponseEntity.ok().body(movies);
    }

    @PutMapping(value = "/add-movie-to-favorites/{movieId}")
    public ResponseEntity<User> addFavoriteMovie(@RequestHeader(name = "User-Id", required = true) Long userId,
                                                 @PathVariable(required = true) Long movieId){
        return ResponseEntity.ok().body(favoritesService.addMovieToFavoriteUser(movieId, userId));
    }

    @DeleteMapping(value = "/remove-movie-from-favorites/{movieId}")
    public ResponseEntity<User> removeFavoriteMovie(@RequestHeader(name = "User-Id", required = true) Long userId,
                                                    @PathVariable(required = true) Long movieId){
        return ResponseEntity.ok().body(favoritesService.removeMovieToFavoriteUser(movieId, userId));
    }

    @GetMapping(value = "/find-movie-which-none-favorites")
    public ResponseEntity<Set<Movie>> findMovieWhichNoneFavorites(@RequestHeader(name = "User-Id", required = true) Long userId,
                                                                  @RequestParam(required = true, defaultValue = "sql") String loaderType){
        WebserviceFilmApplication.log.info("loaderType:{}", loaderType);
        if(loaderType.equals("sql")){
            return ResponseEntity.ok().body(searchServiceSQL.findMoviesWhichNoneFavorites(userId));
        } else if(loaderType.equals("inMemory")){
            return ResponseEntity.ok().body(searchServiceInMemory.findMoviesWhichNoneFavorites(userId));
        }
        WebserviceFilmApplication.log.error("loaderType:{} is not equals \"sql\" or \"inMemory\"", loaderType);
        throw new BadRequestException();
    }

}
