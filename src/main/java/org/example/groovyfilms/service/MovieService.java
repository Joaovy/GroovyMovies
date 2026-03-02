package org.example.groovyfilms.service;

import org.apache.catalina.LifecycleState;
import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if(!movieRepository.existsById(id)){
            throw new RuntimeException("Filme não encontrado!");
        }
        movieRepository.deleteById(id);
        return null;
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
}
