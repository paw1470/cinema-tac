package pl.paw1470.cinematac.core.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.core.DAO.SeanceDAO;
import pl.paw1470.cinematac.core.entity.Seance;
import pl.paw1470.cinematac.core.mapper.SeanceMapper;
import pl.paw1470.cinematac.core.mapper.impl.SeanceMapperImpl;
import pl.paw1470.cinematac.core.repository.MovieRepository;
import pl.paw1470.cinematac.core.repository.SeanceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SeanceRepositoryImpl implements SeanceRepository {

    private SeanceMapper seanceMapper = new SeanceMapperImpl();


    @Autowired
    MovieRepository movieRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Seance getById(Long id) {
        return entityManager.find(Seance.class, id);
    }

    @Override
    public SeanceDAO getByIdDao(Long id) {
        Seance seance = getById(id);
        return seanceMapper.entityToDao(seance);
    }

    @Override
    public SeanceDAO setReservationAvailability(Long id, boolean reservationAvailability) {
        Seance seance = getById(id);
        if(reservationAvailability){    //pozwalam rezerwowac i kupowac bilety
            seance.openReservation();
            seance.openTicket();
        } else {
            seance.closeReservation();
            //todo zamykanie wszystkich rezerwacji
        }
        entityManager.flush();
        return getByIdDao(id);
    }



    @Override
    public SeanceDAO setTicketAvailability(Long id, boolean ticketAvailability) {
        Seance seance = getById(id);
        if(ticketAvailability){
            seance.openTicket();
        } else {
            seance.closeTicket();   //zabraniam kupowac bilety i rezerwowac
            seance.closeReservation();
            //todo zamykanie rezerwacji
        }
        entityManager.flush();
        return getByIdDao(id);
    }


    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<SeanceDAO> getAllSeanceDaoList() {
        Query query = entityManager.createQuery("FROM seance");
        List<Seance> seanceList = query.getResultList();
        return seanceMapper.listToDao(seanceList);
    }

    @Override
    public List<SeanceDAO> getAllSeanceByMovieDaoList(Long movieId) {   //todo
        return null;
    }

    @Override
    public List<SeanceDAO> getAllSeanceByCinemaDaoList(Long cinemaId) {     //todo
        return null;
    }
}
