package org.example.groovyfilms.service;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.domain.model.User;
import org.example.groovyfilms.repository.MovieRepository;
import org.example.groovyfilms.repository.ReviewRepository;
import org.example.groovyfilms.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final MovieRepository movieRepository;

    private final UserRepository UserRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.UserRepository = userRepository;
    }
}
