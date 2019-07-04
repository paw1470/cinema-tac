package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;

import java.util.List;

public interface TicketMapper {
    Ticket daoToEntity(TicketDAO ticketDAO, Seance seance);
    TicketDAO entityToDao(Ticket ticket);
    List<TicketDAO> listToDao(List<Ticket> ticketList);
}
