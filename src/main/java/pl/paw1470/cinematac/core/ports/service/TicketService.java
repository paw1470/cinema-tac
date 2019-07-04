package pl.paw1470.cinematac.core.ports.service;

import pl.paw1470.cinematac.core.model.TicketDAO;

import java.util.List;

public interface TicketService {
    TicketDAO add(TicketDAO ticketDAO);
    List<TicketDAO> getAll();
    List<TicketDAO> getByMovie(Long id);
    List<TicketDAO> getBySeance(Long id);
    void removeAll();
}
