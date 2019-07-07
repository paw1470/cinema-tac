package pl.paw1470.cinematac.adapters.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.maper.RoomMapperImpl;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.adapters.db.entity.Room;
import pl.paw1470.cinematac.core.ports.mapper.RoomMapper;
import pl.paw1470.cinematac.core.ports.repository.CinemaRepository;
import pl.paw1470.cinematac.core.ports.repository.RoomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {

    private RoomMapper roomMapper = new RoomMapperImpl();

    @Autowired
    CinemaRepository cinemaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room getById(Long id){
        return entityManager.find(Room.class, id);
    }

    @Override
    public RoomDAO getByIdDao(Long id) {
        Room room = getById(id);
        return roomMapper.entityToDao(room);
    }

    @Override
    public RoomDAO add(RoomDAO roomDAO) {
        Cinema cinema = cinemaRepository.getById(roomDAO.getCinema().getId());
        Room room = roomMapper.daoToEntity(roomDAO, cinema);
        entityManager.persist(room);
        return roomMapper.entityToDao(room);
    }

    @Override
    public RoomDAO update(RoomDAO roomDAO) {
        Room room = getById(roomDAO.getId());
        roomMapper.update(room, roomDAO);
        entityManager.flush();
        return getByIdDao(roomDAO.getId());
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<RoomDAO> getAllRoomDaoList() {
        Query query = entityManager.createQuery("FROM Room");
        List<Room> roomList = query.getResultList();
        return roomMapper.listToDao(roomList);
    }

    @Override
    public List<RoomDAO> getAllRoomByCinemaList(Long cinemaId) {    //todo
        Query query = entityManager.createQuery("FROM Room R WHERE R.cinema.id =:cinemaId");
        query.setParameter("cinemaId", cinemaId);
        List<Room> roomList = query.getResultList();
        return roomMapper.listToDao(roomList);
    }

    @Override
    public void deleteAll() {
        String hql = "DELETE FROM Room ";
        Query query = entityManager.createQuery(hql);
        int result = query.executeUpdate();
    }
}
