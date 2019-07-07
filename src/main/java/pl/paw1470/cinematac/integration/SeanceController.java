package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.ports.service.ReservationService;
import pl.paw1470.cinematac.core.ports.service.SeanceService;

import java.util.List;

@RestController
@RequestMapping("/api/seance")
public class SeanceController {
    private SeanceService seanceService;
    private ReservationService reservationService;

    public SeanceController(SeanceService seanceService, ReservationService reservationService) {
        this.seanceService = seanceService;
        this.reservationService = reservationService;
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

    @GetMapping("/free")
    public boolean[][] getFreePlaces(@PathVariable Long id) {
        return reservationService.getFreePlacesBySeance(id);
    }

        @PostMapping("/reservation/{id}/block")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('CASHIER')")
    public SeanceDAO blockReservation(@PathVariable Long id) {
        return seanceService.setReservationAvailability(id, false);
    }

}
