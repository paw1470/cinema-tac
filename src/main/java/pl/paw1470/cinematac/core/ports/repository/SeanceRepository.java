package pl.paw1470.cinematac.core.ports.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.adapters.db.entity.Seance;

import java.util.List;

@Repository
public interface SeanceRepository {
    Seance getById(Long id);
    SeanceDAO getByIdDao(Long id);
    SeanceDAO add(SeanceDAO seanceDAO);
    SeanceDAO setReservationAvailability(Long id, boolean reservationAvailability);
    SeanceDAO setTicketAvailability(Long id, boolean ticketAvailability);
    void delete(Long id);
    List<SeanceDAO> getAllSeanceDaoList();
    List<SeanceDAO> getAllSeanceByMovieDaoList(Long movieId);
    List<SeanceDAO> getAllSeanceByCinemaDaoList(Long cinemaId);
    void deleteAll();
}
