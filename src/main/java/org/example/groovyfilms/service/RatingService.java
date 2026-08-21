package org.example.groovyfilms.service;

import org.example.groovyfilms.domain.model.Rating;
import org.example.groovyfilms.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getRatingsByMovieId(Long movieId) {
        return ratingRepository.findByMovieId(movieId);
    }

    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
}
