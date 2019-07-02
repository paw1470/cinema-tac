package pl.paw1470.cinematac.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.DAO.SeanceDAO;
import pl.paw1470.cinematac.entity.Seance;

import java.util.List;

@Repository
public interface SeanceRepository {
    Seance getById(Long id);
    SeanceDAO getByIdDao(Long id);
    SeanceDAO setReservationAvailability(Long id, boolean reservationAvailability);
    SeanceDAO setTicketAvailability(Long id, boolean ticketAvailability);
    void delete(Long id);
    List<SeanceDAO> getAllSeanceDaoList();
    List<SeanceDAO> getAllSeanceByMovieDaoList(Long movieId);
    List<SeanceDAO> getAllSeanceByCinemaDaoList(Long cinemaId);
}
