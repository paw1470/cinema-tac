package pl.paw1470.cinematac.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.DAO.MovieDAO;
import pl.paw1470.cinematac.DAO.RoomDAO;
import pl.paw1470.cinematac.entity.Cinema;
import pl.paw1470.cinematac.entity.Room;
import pl.paw1470.cinematac.mapper.RoomMapper;
import pl.paw1470.cinematac.mapper.impl.RoomMapperImpl;
import pl.paw1470.cinematac.repository.CinemaRepository;
import pl.paw1470.cinematac.repository.RoomRepository;

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

    protected Room getById(Long id){
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
        entityManager.persist(cinema);
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
        entityManager.remove(getByIdDao(id));
    }

    @Override
    public List<RoomDAO> getAllRoomDaoList() {
        Query query = entityManager.createQuery("FROM room");
        List<Room> roomList = query.getResultList();
        return roomMapper.listToDao(roomList);
    }

    @Override
    public List<RoomDAO> getAllRoomByCinemaList(Long cinemaId) {
        return null;
    }
}
