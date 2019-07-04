package pl.paw1470.cinematac.core.ports.repository;

import pl.paw1470.cinematac.adapters.db.entity.Reservation;
import pl.paw1470.cinematac.core.model.ReservationDAO;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation getById(Long id);
    Optional<ReservationDAO> getByIdDao(Long id);
    Optional<ReservationDAO> add(ReservationDAO reservationDAO);
    Optional<ReservationDAO> confirm(Long id);
    List<ReservationDAO> getAll();
    List<ReservationDAO> getAllBySeance(Long seanceId);
    void delete(Long id);
    int deleteNotConfirmedBySeance(Long cinemaId);
    void deleteAll();
    boolean isPlaceFree(ReservationDAO reservationDAO);
}
