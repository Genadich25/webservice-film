package com.webservicewithapi.webservicefilm.services.Movie;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.repositories.MovieRepository;
import com.webservicewithapi.webservicefilm.repositories.UserRepository;
import com.webservicewithapi.webservicefilm.services.interfaces.MovieSearchService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MovieSearchServiceInMemoryImpl implements MovieSearchService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public MovieSearchServiceInMemoryImpl(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Set<Movie> findMoviesWhichNoneFavorites(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            Set<Movie> moviesUser = user.get().getFavoriteMovies();
            Set<Movie> moviesAll = new HashSet<>(movieRepository.findAll());
            WebserviceFilmApplication.log.info("loaderType=\"inMemory\". Movies that are not in the user's favorites {} ", user);
            return moviesAll.stream()
                    .filter(e -> !moviesUser.contains(e))
                    .collect(Collectors.toSet());
        }
        WebserviceFilmApplication.log.error("loaderType=\"inMemory\". UserId:{} not found", userId);
        throw new NotFoundException("");
    }
}
