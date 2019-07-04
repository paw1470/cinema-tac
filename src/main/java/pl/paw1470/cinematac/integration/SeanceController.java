package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.ports.service.SeanceService;

import java.util.List;

@RestController
@RequestMapping("/api/seance")
public class SeanceController {
    private SeanceService seanceService;

    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public SeanceDAO createSeance(@RequestBody SeanceDAO seanceDAO) {
        return seanceService.add(seanceDAO);
    }

    @GetMapping()
    public List<SeanceDAO> getAll() {
        return seanceService.getAll();
    }

    @GetMapping("/cinema/{id}")
    public List<SeanceDAO> getByCity(@PathVariable Long id) {
        return seanceService.getAllByCinema(id);
    }

}
