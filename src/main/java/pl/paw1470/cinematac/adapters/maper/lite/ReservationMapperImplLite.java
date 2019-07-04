package pl.paw1470.cinematac.adapters.maper.lite;

import pl.paw1470.cinematac.adapters.db.entity.Reservation;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;
import pl.paw1470.cinematac.adapters.maper.SeanceMapperImpl;
import pl.paw1470.cinematac.core.model.ReservationDAO;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.core.ports.mapper.ReservationMapper;
import pl.paw1470.cinematac.core.ports.mapper.SeanceMapper;
import pl.paw1470.cinematac.core.ports.mapper.TicketMapper;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapperImplLite implements ReservationMapper {

    private SeanceMapper seanceMapper = new SeanceMapperImplLite();
    private TicketMapper ticketMapper = new TicketMapperImplLite();

    @Override
    public Reservation daoToEntity(ReservationDAO reservationDAO, Seance seance, Ticket ticket) {
        Reservation reservation = new Reservation(seance,
                reservationDAO.isConfirmed(),
                ticket,
                reservationDAO.getEmail(),
                reservationDAO.getTel(),
                reservationDAO.getName(),
                reservationDAO.getSurname(),
                reservationDAO.getRow(),
                reservationDAO.getColumn()
        );
        return reservation;
    }

    @Override
    public ReservationDAO entityToDao(Reservation reservation) {
        ReservationDAO reservationDAO = new ReservationDAO(reservation.getId(),
                seanceMapper.entityToDao(reservation.getSeance()),
                reservation.isConfirmed(),
                ticketMapper.entityToDao(reservation.getTicket()),
                reservation.getEmail(),
                reservation.getTel(),
                reservation.getName(),
                reservation.getSurname(),
                reservation.getRow(),
                reservation.getColumn()
                );
        return reservationDAO;
    }

    @Override
    public List<ReservationDAO> listToDao(List<Reservation> reservationList) {
        List<ReservationDAO> reservationDAOList = new ArrayList<>();
        for (Reservation r : reservationList) {
            reservationDAOList.add(entityToDao(r));
        }
        return reservationDAOList;
    }

    @Override
    public ReservationDAO fastDao(SeanceDAO seance, TicketDAO ticket, boolean isConfirmed, String info, int row, int column){
        ReservationDAO reservationDAO = new ReservationDAO(1L,
                seance,
                isConfirmed,
                ticket,
                "e" + info,
                "t" + info,
                "n" + info,
                "s" + info,
                row,
                column
        );
        return reservationDAO;
    }
}
