package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.ports.service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public List<CinemaDAO> getAll() {
        return cinemaService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CinemaDAO create(@RequestBody CinemaDAO cinema) {
        return cinemaService.add(cinema);
    }
}
