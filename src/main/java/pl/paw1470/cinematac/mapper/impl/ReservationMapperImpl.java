package pl.paw1470.cinematac.mapper.impl;

import pl.paw1470.cinematac.DAO.ReservationDAO;
import pl.paw1470.cinematac.entity.Reservation;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;
import pl.paw1470.cinematac.mapper.ReservationMapper;
import pl.paw1470.cinematac.mapper.SeanceMapper;

import java.util.List;

public class ReservationMapperImpl implements ReservationMapper {

    private SeanceMapper seanceMapper = new SeanceMapperImpl();

    @Override
    public Reservation daoToEntity(ReservationDAO reservationDAO, Seance seance, Ticket ticket) {
        Reservation reservation = new Reservation(seance,
                reservationDAO.isConfirmed(),
                ticket,
                reservationDAO.getEmail(),
                reservationDAO.getTel(),
                reservationDAO.getName(),
                reservationDAO.getSurname());
        return reservation;
    }

    @Override
    public ReservationDAO entityToDao(Reservation reservation) {
        ReservationDAO reservationDAO = new ReservationDAO(reservation.getId(),
                seanceMapper.entityToDao(reservation.getSeance()),
                reservation.isConfirmed(),
                reservation.getTicket().getTicketType(),
                reservation.getTicket().getPrice(),
                reservation.getEmail(),
                reservation.getTel(),
                reservation.getName(),
                reservation.getSurname()
                );
        return reservationDAO;
    }

    @Override
    public List<Reservation> listToDao(List<Reservation> reservationListn) {
        return null;
    }
}
