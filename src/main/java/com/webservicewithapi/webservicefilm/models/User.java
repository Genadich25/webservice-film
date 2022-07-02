package com.webservicewithapi.webservicefilm.models;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Movie.class)
    @JoinTable(
            name = "users_movies_favorites",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_movie"))
    private Set<Movie> favoriteMovies = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Set<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public void addMovie(Movie movie){
        favoriteMovies.add(movie);
    }

    public void removeMovie(Movie movie){
        favoriteMovies.remove(movie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email) && username.equals(user.username) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
