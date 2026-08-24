package org.example.groovyfilms.service;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.domain.model.Rating;
import org.example.groovyfilms.domain.model.User;
import org.example.groovyfilms.repository.MovieRepository;
import org.example.groovyfilms.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    public Rating saveOrUpdate(Long movieId, int score, User user) {
        Optional<Rating> existing = ratingRepository.findByMovieIdAndUserId(movieId, user.getId());

        if (existing.isPresent()) {
            existing.get().setScore(score);
            return ratingRepository.save(existing.get());
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado!"));

        Rating rating = new Rating();
        rating.setScore(score);
        rating.setUser(user);
        rating.setMovie(movie);
        return ratingRepository.save(rating);
    }

    public double getAverageRating(Long movieId) {
        return ratingRepository.findAverageScoreByMovieId(movieId).orElse(0.0);
    }

    public int getUserScore(Long movieId, Long userId) {
        return ratingRepository.findByMovieIdAndUserId(movieId, userId)
                .map(Rating::getScore)
                .orElse(0);
    }
}
