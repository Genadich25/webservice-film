package com.webservicewithapi.webservicefilm.services.interfaces;

import com.webservicewithapi.webservicefilm.models.Movie;


import java.util.Set;


public interface MovieSearchService {
    public Set<Movie> findMoviesWhichNoneFavorites(Long userId);
}
