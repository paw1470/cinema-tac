package pl.paw1470.cinematac.core.ports.service;

import pl.paw1470.cinematac.core.model.SeanceDAO;

import java.util.List;

public interface SeanceService {
    SeanceDAO add(SeanceDAO seanceDAO);
    List<SeanceDAO> getAll();
    List<SeanceDAO> getAllByCinema(Long id);
    SeanceDAO setTicketAvailability(Long id, boolean availability);
    SeanceDAO setReservationAvailability(Long id, boolean availability);
    void removeAll();

}
