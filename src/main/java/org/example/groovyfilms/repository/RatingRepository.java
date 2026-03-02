package org.example.groovyfilms.repository;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.domain.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovie(Movie movie);

}
