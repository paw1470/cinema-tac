package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.ReservationDAO;
import pl.paw1470.cinematac.core.ports.service.ReservationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/cancel/{id}")
    public void cancel(@PathVariable Long id) {
        reservationService.removeById(id);
    }

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ReservationDAO> createReservation(@RequestBody ReservationDAO reservation) {
        return reservationService.add(reservation);
    }

    @PostMapping("/confirm/{id}")
    @PreAuthorize("hasRole('CASHIER')")
    public Optional<ReservationDAO> confirm(@PathVariable Long id) {
        reservationService.confirmReservation(id);
        return reservationService.getById(id);
    }

}
