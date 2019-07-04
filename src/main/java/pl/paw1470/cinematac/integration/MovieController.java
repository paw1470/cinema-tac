package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.MovieDAO;
import pl.paw1470.cinematac.core.ports.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDAO> getAll() {
        return movieService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public MovieDAO create(@RequestBody MovieDAO movie) {
        return movieService.add(movie);
    }
}
