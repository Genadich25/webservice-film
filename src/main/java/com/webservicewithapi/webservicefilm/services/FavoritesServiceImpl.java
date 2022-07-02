package com.webservicewithapi.webservicefilm.services;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.exceptions.EmptyIsFavoritesSetException;
import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.repositories.MovieRepository;
import com.webservicewithapi.webservicefilm.repositories.UserRepository;
import com.webservicewithapi.webservicefilm.services.interfaces.FavoritesService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Set;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    private final UserRepository userRepository;

    private final MovieRepository movieRepository;

    public FavoritesServiceImpl(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public User addMovieToFavoriteUser(Long movieId, Long userId){
        Movie movie = movieRepository.findById(movieId).get();
        User user = userRepository.findById(userId).get();
        user.addMovie(movie);
        userRepository.save(user);
        WebserviceFilmApplication.log.info("{} saved in favorites User's set {}", movie.getTitle(), userId);
        return user;
    }

    @Override
    public User removeMovieToFavoriteUser(Long movieId, Long userId){
        Movie movie = movieRepository.findById(movieId).get();
        User user = userRepository.findById(userId).get();
        Set<Movie> movies = user.getFavoriteMovies();
        if(movies.size() != 0) {
            if(movies.contains(movie)){
                user.removeMovie(movie);
                userRepository.save(user);
                WebserviceFilmApplication.log.info("{} removed in favorites User's set {}", movie.getTitle(), userId);
                return user;
            }
            WebserviceFilmApplication.log.error("{} none in favorite User set {}", movie, user);
            throw new NotFoundException("");
        }
        WebserviceFilmApplication.log.error("Favorites User:{} set is empty", user);
        throw new EmptyIsFavoritesSetException();
    }
}
