package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.core.DAO.ReservationDAO;
import pl.paw1470.cinematac.adapters.db.entity.Reservation;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;

import java.util.List;

public interface ReservationMapper {
    Reservation daoToEntity(ReservationDAO reservationDAO, Seance seance, Ticket ticket);
    ReservationDAO entityToDao(Reservation reservation);
    List<Reservation> listToDao(List<Reservation> reservationListn);
}
