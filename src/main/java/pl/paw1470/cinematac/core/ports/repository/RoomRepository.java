package pl.paw1470.cinematac.core.ports.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.core.DAO.RoomDAO;
import pl.paw1470.cinematac.adapters.db.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository {
    Room getById(Long id);
    RoomDAO getByIdDao(Long id);
    RoomDAO add(RoomDAO roomDAO);
    RoomDAO update(RoomDAO roomDAO);
    void delete(Long id);
    List<RoomDAO> getAllRoomDaoList();
    List<RoomDAO> getAllRoomByCinemaList(Long cinemaId);
}
