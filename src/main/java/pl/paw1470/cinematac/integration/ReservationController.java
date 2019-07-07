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

    @PostMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancel(@PathVariable Long id) {
        reservationService.removeById(id);
    }

    @GetMapping("/{id}")
    public Optional<ReservationDAO> get(@PathVariable Long id) {
        return reservationService.getById(id);
    }

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ReservationDAO> createReservation(@RequestBody ReservationDAO reservation) {
        return reservationService.add(reservation);
    }

    @PostMapping("/{id}/buy")
    @PreAuthorize("hasRole('CASHIER')")
    public Optional<ReservationDAO> confirm(@PathVariable Long id) {
        reservationService.confirmReservation(id);
        return reservationService.getById(id);
    }

    @PostMapping("/buy")
    @PreAuthorize("hasRole('CASHIER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<ReservationDAO> createReservationwithConfirm(@RequestBody ReservationDAO reservation) {
        Optional<ReservationDAO>  reservationDAO = reservationService.add(reservation);
        reservationService.confirmReservation(reservationDAO.get().getId());
        return reservationService.getById(reservationDAO.get().getId());
    }

}
