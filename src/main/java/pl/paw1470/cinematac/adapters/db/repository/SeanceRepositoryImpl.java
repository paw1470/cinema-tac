package pl.paw1470.cinematac.adapters.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.db.entity.Movie;
import pl.paw1470.cinematac.adapters.db.entity.Room;
import pl.paw1470.cinematac.adapters.maper.SeanceMapperImpl;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.core.ports.repository.MovieRepository;
import pl.paw1470.cinematac.core.ports.repository.ReservationRepository;
import pl.paw1470.cinematac.core.ports.repository.RoomRepository;
import pl.paw1470.cinematac.core.ports.repository.SeanceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SeanceRepositoryImpl implements SeanceRepository {

    private SeanceMapperImpl seanceMapper = new SeanceMapperImpl();

    @Autowired
    RoomRepository roomRepository;

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
    public SeanceDAO add(SeanceDAO seanceDAO) {
        Movie movie = movieRepository.getById(seanceDAO.getMovie().getId());
        Room room = roomRepository.getById(seanceDAO.getRoom().getId());
        Seance seance = seanceMapper.daoToEntity(seanceDAO, movie, room);
        entityManager.persist(seance);
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
        Query query = entityManager.createQuery("FROM Seance");
        List<Seance> seanceList = query.getResultList();
        return seanceMapper.listToDao(seanceList);
    }

    @Override
    public List<SeanceDAO> getAllSeanceByMovieDaoList(Long movieId) {
        Query query = entityManager.createQuery("FROM Seance S WHERE S.movie.id = :movieId");
        query.setParameter("movieId", movieId);
        List<Seance> seanceList = query.getResultList();
        return seanceMapper.listToDao(seanceList);
    }

    @Override
    public List<SeanceDAO> getAllSeanceByCinemaDaoList(Long cinemaId) {     //todo
        Query query = entityManager.createQuery("FROM Seance S WHERE S.room.cinema.id =:cinemaId");
        query.setParameter("cinemaId", cinemaId);
        List<Seance> seanceList = query.getResultList();
        return seanceMapper.listToDao(seanceList);
    }

    @Override
    public void deleteAll() {
        String hql = "DELETE FROM Seance ";
        Query query = entityManager.createQuery(hql);
        int result = query.executeUpdate();
    }
}
