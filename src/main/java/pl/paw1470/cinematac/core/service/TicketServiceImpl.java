package pl.paw1470.cinematac.core.service;

import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.core.ports.repository.TicketRepository;
import pl.paw1470.cinematac.core.ports.service.TicketService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketDAO add(TicketDAO ticketDAO) {
        return ticketRepository.add(ticketDAO);
    }

    @Override
    public List<TicketDAO> getAll() {
        return ticketRepository.getAllTicketDaoList();
    }

    @Override
    public List<TicketDAO> getByMovie(Long id) {
        return null;
    }

    @Override
    public List<TicketDAO> getBySeance(Long id) {
        return ticketRepository.getAllTicketBySeanceDaoList(id);
    }

    @Override
    public void removeAll() {
        ticketRepository.deleteAll();
    }


}
