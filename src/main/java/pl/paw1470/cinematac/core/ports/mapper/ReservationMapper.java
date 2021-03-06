package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.core.model.ReservationDAO;
import pl.paw1470.cinematac.adapters.db.entity.Reservation;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.model.TicketDAO;

import java.util.List;

public interface ReservationMapper {
    Reservation daoToEntity(ReservationDAO reservationDAO, Seance seance, Ticket ticket);
    ReservationDAO entityToDao(Reservation reservation);
    List<ReservationDAO> listToDao(List<Reservation> reservationList);
     ReservationDAO fastDao(SeanceDAO seance, TicketDAO ticket, boolean isConfirmed, String info, int row, int column);
}
