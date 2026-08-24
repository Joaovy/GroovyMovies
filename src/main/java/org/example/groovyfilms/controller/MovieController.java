package org.example.groovyfilms.controller;

import org.example.groovyfilms.domain.model.Movie;
import org.example.groovyfilms.domain.model.User;
import org.example.groovyfilms.service.MovieService;
import org.example.groovyfilms.service.RatingService;
import org.example.groovyfilms.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;
    private final UserService userService;

    public MovieController(MovieService movieService, RatingService ratingService, UserService userService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        Map<Long, Double> averageRatings = movies.stream()
                .collect(Collectors.toMap(Movie::getId, m -> ratingService.getAverageRating(m.getId())));
        model.addAttribute("movies", movies);
        model.addAttribute("averageRatings", averageRatings);
        return "movies";
    }

    @GetMapping("/{id}")
    public String movieDetails(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("movie", movieService.findById(id));
        model.addAttribute("averageRating", ratingService.getAverageRating(id));
        model.addAttribute("userScore", ratingService.getUserScore(id, user.getId()));
        return "movie-details";
    }

    @GetMapping("/new")
    public String formNewMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-form";
    }

    @PostMapping
    public String createMovie(@ModelAttribute Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String formEditMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "movie-form";
    }

    @PostMapping("/{id}/edit")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.save(movie);
        return "redirect:/movies/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}
