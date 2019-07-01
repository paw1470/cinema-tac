package pl.paw1470.cinematac.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.DAO.MovieDAO;
import pl.paw1470.cinematac.DAO.RoomDAO;
import pl.paw1470.cinematac.entity.Cinema;

import java.util.List;

@Repository
public interface RoomRepository {
    RoomDAO getByIdDao(Long id);
    RoomDAO add(RoomDAO roomDAO);
    RoomDAO update(RoomDAO roomDAO);
    void delete(Long id);
    List<RoomDAO> getAllRoomDaoList();
    List<RoomDAO> getAllRoomByCinemaList(Long cinemaId);
}
