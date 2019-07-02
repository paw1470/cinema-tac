package pl.paw1470.cinematac.core.mapper;

import pl.paw1470.cinematac.core.DAO.TicketDAO;
import pl.paw1470.cinematac.core.entity.Seance;
import pl.paw1470.cinematac.core.entity.Ticket;

import java.util.List;

public interface TicketMapper {
    Ticket daoToEntity(TicketDAO ticketDAO, Seance seance);
    TicketDAO entityToDao(Ticket ticket);
    List<TicketDAO> listToDao(List<Ticket> ticketList);
}
