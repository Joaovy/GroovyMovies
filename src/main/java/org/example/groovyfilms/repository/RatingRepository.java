package org.example.groovyfilms.repository;

import org.example.groovyfilms.domain.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovieId(Long movieId);

    Optional<Rating> findByMovieIdAndUserId(Long movieId, Long userId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.movie.id = :movieId")
    Optional<Double> findAverageScoreByMovieId(@Param("movieId") Long movieId);
}
