package pl.paw1470.cinematac.repository;

import pl.paw1470.cinematac.DAO.TicketDAO;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket getById(Long id);
    TicketDAO getByIdDao(Long id);
    TicketDAO setActive(Long id, boolean isActive);
    TicketDAO add(TicketDAO ticketDAO);
    void delete(Long id);
    List<TicketDAO> getAllTicketDaoList();
    List<TicketDAO> getAllTIcketBySeanceDaoList(Long seanceId);
}
