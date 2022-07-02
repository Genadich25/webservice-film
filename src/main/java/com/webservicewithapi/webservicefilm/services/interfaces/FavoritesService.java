package com.webservicewithapi.webservicefilm.services.interfaces;

import com.webservicewithapi.webservicefilm.models.Movie;
import com.webservicewithapi.webservicefilm.models.User;

import java.util.Set;

public interface FavoritesService {
    public User addMovieToFavoriteUser(Long movieId, Long userId);

    public User removeMovieToFavoriteUser(Long movieId, Long userId);
}
