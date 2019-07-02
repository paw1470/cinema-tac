package pl.paw1470.cinematac.mapper.impl;

import pl.paw1470.cinematac.DAO.TicketDAO;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;
import pl.paw1470.cinematac.mapper.SeanceMapper;
import pl.paw1470.cinematac.mapper.TicketMapper;

import java.util.ArrayList;
import java.util.List;

public class TicketMapperImpl implements TicketMapper {
    private SeanceMapper seanceMapper = new SeanceMapperImpl();

    @Override
    public Ticket daoToEntity(TicketDAO ticketDAO, Seance seance) {
        Ticket ticket = new Ticket(ticketDAO.getTicketType(),
                seance,
                ticketDAO.getPrice(),
                ticketDAO.isActive()
        );
        return ticket;
    }

    @Override
    public TicketDAO entityToDao(Ticket ticket) {
        TicketDAO ticketDAO = new TicketDAO(ticket.getId(),
                ticket.getTicketType(),
                ticket.getPrice(),
                ticket.isActive(),
                seanceMapper.entityToDao(ticket.getTicketSeance())
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
}
