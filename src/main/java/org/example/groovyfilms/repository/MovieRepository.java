package org.example.groovyfilms.repository;

import org.example.groovyfilms.domain.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);
    boolean existsByTitle(String title);

}
