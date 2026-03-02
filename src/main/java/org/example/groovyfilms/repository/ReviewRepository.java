package org.example.groovyfilms.repository;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.domain.model.Rating;
import org.example.groovyfilms.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);

}
