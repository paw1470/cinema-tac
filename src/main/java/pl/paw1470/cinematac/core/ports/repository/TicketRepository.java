package pl.paw1470.cinematac.core.ports.repository;

import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket getById(Long id);
    TicketDAO getByIdDao(Long id);
    TicketDAO setActive(Long id, boolean isActive);
    TicketDAO add(TicketDAO ticketDAO);
    void delete(Long id);
    List<TicketDAO> getAllTicketDaoList();
    List<TicketDAO> getAllTicketBySeanceDaoList(Long seanceId);
    void deleteAll();
}
