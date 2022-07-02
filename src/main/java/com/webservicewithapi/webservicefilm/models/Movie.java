package com.webservicewithapi.webservicefilm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "id_movie")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "poster_path")
    private String poster_path;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteMovies", fetch = FetchType.EAGER)
    private Set<User> userHaveMovieInFavorites = new HashSet<>();

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Set<User> getUserHaveMovieInFavorites() {
        return userHaveMovieInFavorites;
    }

    public void setUserHaveMovieInFavorites(Set<User> userHaveMovieInFavorites) {
        this.userHaveMovieInFavorites = userHaveMovieInFavorites;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id) && title.equals(movie.title) && Objects.equals(poster_path, movie.poster_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, poster_path);
    }
}
