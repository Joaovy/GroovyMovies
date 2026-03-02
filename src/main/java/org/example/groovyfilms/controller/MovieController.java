package org.example.groovyfilms.controller;


import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
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

    @GetMapping("/{id}")
    public String movieDetails(@PathVariable Long id, Model model){
        model.addAttribute("movie", movieService.findById(id));
        return "movie-details";
    }

    @GetMapping("/new")
    public String formNewMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "movie-form";
    }

    @PostMapping
    public String createMovie(@ModelAttribute Movie movie){
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String formEditMovie(@PathVariable Long id, Model model){
        model.addAttribute("movie", movieService.findById(id));
        return "movie-form";
    }

    @PostMapping("/{id}/edit")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie){
        movie.setId(id);
        movieService.save(movie);
        return "redirect:/movies/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}