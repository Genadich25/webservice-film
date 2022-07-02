package com.webservicewithapi.webservicefilm.services.Movie;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.exceptions.PageNotExistException;
import com.webservicewithapi.webservicefilm.models.BaseResult;
import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.repositories.MovieRepository;
import com.webservicewithapi.webservicefilm.services.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${movie.apikey}")
    private String apiKey;

    @Value("${movie.url.discover.movie}")
    private String url;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovieByPages(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        List<Movie> movies = movieRepository.findAll(pageRequest).getContent();
        if(movies.size() > 0) {
            return movies;
        }
        WebserviceFilmApplication.log.error("{} does not exist" , page);
        throw new PageNotExistException();
    }

    @Scheduled(cron = "0 0 0/3 * * *")
    private void getMovie() {
        WebserviceFilmApplication.log.info("Scheduled start");
        int finalPage = 5;
        boolean isNewMoviesPage = true;
        for (int i = 1; i <= finalPage; i++) {
            BaseResult baseResult = restTemplate.exchange(url + "?page=" + i + "&api_key="+ apiKey, HttpMethod.GET, new HttpEntity<>(HttpEntity.EMPTY), BaseResult.class).getBody();
            if(baseResult != null){
                for (int j = 0; j < baseResult.getResults().length; j++) {
                    Movie movie = baseResult.getResults()[j];
                    if(movieRepository.findMovieByTitle(movie.getTitle()).isPresent()){
                        isNewMoviesPage = false;
                    } else {
                        movieRepository.save(movie);
                    }
                }
                if(!isNewMoviesPage){
                    finalPage++;
                    isNewMoviesPage = true;
                }
            }
        }
        WebserviceFilmApplication.log.info("Scheduled end");
    }
}
