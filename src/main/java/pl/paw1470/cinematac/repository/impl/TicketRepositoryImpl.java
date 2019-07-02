package pl.paw1470.cinematac.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.paw1470.cinematac.DAO.TicketDAO;
import pl.paw1470.cinematac.entity.Seance;
import pl.paw1470.cinematac.entity.Ticket;
import pl.paw1470.cinematac.mapper.TicketMapper;
import pl.paw1470.cinematac.mapper.impl.TicketMapperImpl;
import pl.paw1470.cinematac.repository.SeanceRepository;
import pl.paw1470.cinematac.repository.TicketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class TicketRepositoryImpl implements TicketRepository {

    private TicketMapper ticketMapper = new TicketMapperImpl();

    @Autowired
    private SeanceRepository seanceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket getById(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    @Override
    public TicketDAO getByIdDao(Long id) {
        return ticketMapper.entityToDao(getById(id));
    }

    @Override
    public TicketDAO setActive(Long id, boolean isActive) {
        Ticket ticket = getById(id);
        ticket.setActive(isActive);
        entityManager.flush();
        return ticketMapper.entityToDao(ticket);
    }

    @Override
    public TicketDAO add(TicketDAO ticketDAO) {
        Seance seance = seanceRepository.getById(ticketDAO.getSeanceDAO().getId());
        Ticket ticket = ticketMapper.daoToEntity(ticketDAO, seance);
        entityManager.persist(ticket);
        return ticketMapper.entityToDao(ticket);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<TicketDAO> getAllTicketDaoList() {
        Query query = entityManager.createQuery("FROM ticket");
        List<Ticket> ticketList = query.getResultList();
        return ticketMapper.listToDao(ticketList);
    }

    @Override
    public List<TicketDAO> getAllTIcketBySeanceDaoList(Long seanceId) {//todo
        return null;
    }
}
