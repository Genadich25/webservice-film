package com.webservicewithapi.webservicefilm.services.Movie;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.repositories.MovieRepository;
import com.webservicewithapi.webservicefilm.repositories.UserRepository;
import com.webservicewithapi.webservicefilm.services.interfaces.MovieSearchService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieSearchServiceSQLImpl implements MovieSearchService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public MovieSearchServiceSQLImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<Movie> findMoviesWhichNoneFavorites(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            WebserviceFilmApplication.log.info("loaderType=\"sql\". Movies that are not in the user's favorites {} ", user);
            return movieRepository.findMovieWhichNoneFavorites(userId);
        }
        WebserviceFilmApplication.log.error("loaderType=\"sql\". UserId:{} not found", userId);
        throw new NotFoundException("");
    }
}
