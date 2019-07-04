package pl.paw1470.cinematac.adapters.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.db.entity.Reservation;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.adapters.db.entity.Ticket;
import pl.paw1470.cinematac.adapters.maper.lite.ReservationMapperImplLite;
import pl.paw1470.cinematac.core.model.ReservationDAO;
import pl.paw1470.cinematac.core.ports.mapper.ReservationMapper;
import pl.paw1470.cinematac.core.ports.repository.ReservationRepository;
import pl.paw1470.cinematac.core.ports.repository.SeanceRepository;
import pl.paw1470.cinematac.core.ports.repository.TicketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReservationRepositoryImpl implements ReservationRepository {

    private ReservationMapperImplLite reservationMapper = new ReservationMapperImplLite();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Reservation getById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public Optional<ReservationDAO> getByIdDao(Long id) {
        Reservation reservation = getById(id);
        Optional<ReservationDAO> optional = Optional.empty();
        if (reservation != null) {
            optional = Optional.of(reservationMapper.entityToDao(reservation));
        }
        return optional;
    }

    @Override
    public Optional<ReservationDAO> add(ReservationDAO reservationDAO) {
        Ticket ticket = ticketRepository.getById(reservationDAO.getTicket().getId());
        Seance seance = seanceRepository.getById(reservationDAO.getSeance().getId());
        Optional<ReservationDAO> optional = Optional.empty();
        if(isPlaceInRoom(reservationDAO)){
            if (isPlaceFree(reservationDAO)){
                Reservation reservation = reservationMapper.daoToEntity(reservationDAO, seance, ticket);
                entityManager.persist(reservation);
                optional = Optional.of(reservationMapper.entityToDao(reservation));
            }
        }
        return optional;
    }

    boolean isPlaceInRoom(ReservationDAO reservationDAO){
        return ((reservationDAO.getRow() > 0) && (reservationDAO.getColumn() > 0) &&
                (reservationDAO.getRow() <= reservationDAO.getSeance().getRoom().getRows()) &&
                (reservationDAO.getColumn() <= reservationDAO.getSeance().getRoom().getColumns()));
    }

    @Override
    public Optional<ReservationDAO> confirm(Long id) {
        Reservation reservation = getById(id);
        reservation.confirm();
        entityManager.flush();
        return getByIdDao(id);
    }

    @Override
    public List<ReservationDAO> getAll() {
        Query query = entityManager.createQuery("FROM Reservation");
        List<Reservation> reservationList = query.getResultList();
        return reservationMapper.listToDao(reservationList);
    }

    @Override
    public List<ReservationDAO> getAllBySeance(Long seanceId) {
        Query query = entityManager.createQuery("FROM Reservation R WHERE R.seance.id = :seanceId");
        query.setParameter("seanceId", seanceId);
        List<Reservation> reservationList = query.getResultList();
        return reservationMapper.listToDao(reservationList);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public int deleteNotConfirmedBySeance(Long seanceId) {
        Query query = entityManager.createQuery("DELETE FROM Reservation R WHERE R.isConfirmed = false and R.seance.id = :seanceId");
        query.setParameter("seanceId", seanceId);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public void deleteAll() {
        String hql = "DELETE FROM Reservation";
        Query query = entityManager.createQuery(hql);
        int result = query.executeUpdate();
    }

    @Override
    public boolean isPlaceFree(ReservationDAO reservationDAO) {
        Query query = entityManager.createQuery("FROM Reservation R WHERE R.row = :row and R.column = :column");
        query.setParameter("row", reservationDAO.getRow());
        query.setParameter("column", reservationDAO.getColumn());
        List<Reservation> reservationList = query.getResultList();
        return (reservationList.size() == 0);
    }
}
