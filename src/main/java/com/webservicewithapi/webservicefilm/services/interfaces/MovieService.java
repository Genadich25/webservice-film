package com.webservicewithapi.webservicefilm.services.interfaces;

import com.webservicewithapi.webservicefilm.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovieByPages(Integer page, Integer size);
}
