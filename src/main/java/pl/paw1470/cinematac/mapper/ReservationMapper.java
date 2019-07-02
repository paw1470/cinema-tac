package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.DAO.ReservationDAO;
import pl.paw1470.cinematac.entity.Reservation;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;

import java.util.List;

public interface ReservationMapper {
    Reservation daoToEntity(ReservationDAO reservationDAO, Seance seance, Ticket ticket);
    ReservationDAO entityToDao(Reservation reservation);
    List<Reservation> listToDao(List<Reservation> reservationListn);
}
