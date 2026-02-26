package org.example.groovyfilms.controller;


import org.example.groovyfilms.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String listMovies(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movies";
    }

    @GetMapping
    public String movieDetails(@P)


}
