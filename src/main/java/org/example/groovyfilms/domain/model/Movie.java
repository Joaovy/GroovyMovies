package org.example.groovyfilms.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "movie",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "releaseYear"})
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String director;

    @NotBlank
    @Column(nullable = false, length = 5000)
    private String description;

    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private Set<Genre> genres = new HashSet<>();

    @NotBlank
    @Column(nullable = false)
    private String posterUrl;

    @NotNull
    @Min(1888)
    private Integer releaseYear;

    @NotNull
    @Min(1)
    private Integer durationsMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Movie() {
    }

    public Movie(Long id, String title, String director, String description,
                 Set<Genre> genres, String posterUrl,
                 Integer releaseYear, Integer durationsMinutes,
                 LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.description = description;
        this.genres = genres;
        this.posterUrl = posterUrl;
        this.releaseYear = releaseYear;
        this.durationsMinutes = durationsMinutes;
        this.createdAt = createdAt;
    }
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getDurationsMinutes() {
        return durationsMinutes;
    }

    public void setDurationsMinutes(Integer durationsMinutes) {
        this.durationsMinutes = durationsMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
