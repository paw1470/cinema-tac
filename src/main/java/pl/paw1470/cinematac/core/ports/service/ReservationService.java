package pl.paw1470.cinematac.core.ports.service;


import pl.paw1470.cinematac.core.model.ReservationDAO;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<ReservationDAO> add(ReservationDAO reservationDAO);
    Optional<ReservationDAO> getById(Long id);
    List<ReservationDAO> getAll();
    List<ReservationDAO> getBySeance(Long id);
    void removeById(Long id);
    boolean[][] getFreePlacesBySeance(Long id);
    void confirmReservation(Long id);
    void removeAll();
    boolean isPlaceFree(ReservationDAO reservationDAO);
}
