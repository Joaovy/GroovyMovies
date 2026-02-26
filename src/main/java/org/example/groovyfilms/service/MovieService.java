package org.example.groovyfilms.service;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;



    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie findById(Long id){
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Filme não encontrado!"));
    }

    public Movie deleteMovie(Long id){

        if(movieRepository.existsById(id)){
            throw new RuntimeException("Filme não encontrado!");
        }
        movieRepository.deleteById(id);
        return null;
    }

    public Movie findAll(){
        return (Movie) movieRepository.findAll();
    }
}
