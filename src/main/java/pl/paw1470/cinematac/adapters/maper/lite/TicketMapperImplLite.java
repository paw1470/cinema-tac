package pl.paw1470.cinematac.adapters.maper.lite;

import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.core.ports.mapper.SeanceMapper;
import pl.paw1470.cinematac.core.ports.mapper.TicketMapper;

import java.util.ArrayList;
import java.util.List;

public class TicketMapperImplLite implements TicketMapper {
    private SeanceMapper seanceMapper = new SeanceMapperImplLite();

    @Override
    public Ticket daoToEntity(TicketDAO ticketDAO, Seance seance) {
        Ticket ticket = new Ticket(ticketDAO.getTicketType(),
                seance,
                ticketDAO.getPrice(),
                true
        );
        return ticket;
    }

    @Override
    public TicketDAO entityToDao(Ticket ticket) {
        TicketDAO ticketDAO = new TicketDAO(ticket.getId(),
                ticket.getTicketType(),
                ticket.getPrice(),
                true,
                seanceMapper.entityToDao(ticket.getSeance())
        );
        return ticketDAO;
    }

    @Override
    public List<TicketDAO> listToDao(List<Ticket> ticketList) {
        List<TicketDAO> ticketDAOList = new ArrayList<>();
        for (Ticket t : ticketList) {
            ticketDAOList.add(entityToDao(t));
        }
        return ticketDAOList;
    }

    public TicketDAO fastDao(SeanceDAO seance, String type, double price){
        TicketDAO ticketDAO = new TicketDAO(1L,
                type,
                price,
                true,
                seance
        );
        return ticketDAO;
    }
}
