package org.example.groovyfilms.controller;

import org.example.groovyfilms.domain.model.User;
import org.example.groovyfilms.service.RatingService;
import org.example.groovyfilms.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;

    public RatingController(RatingService ratingService, UserService userService) {
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @PostMapping("/{id}/rating")
    public String rateMovie(@PathVariable Long id,
                            @RequestParam int score,
                            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());
        ratingService.saveOrUpdate(id, score, user);
        return "redirect:/movies/" + id;
    }
}
