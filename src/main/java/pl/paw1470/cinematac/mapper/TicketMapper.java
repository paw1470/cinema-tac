package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.DAO.TicketDAO;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;

import java.util.List;

public interface TicketMapper {
    Ticket daoToEntity(TicketDAO ticketDAO, Seance seance);
    TicketDAO entityToDao(Ticket ticket);
    List<TicketDAO> listToDao(List<Ticket> ticketList);
}
