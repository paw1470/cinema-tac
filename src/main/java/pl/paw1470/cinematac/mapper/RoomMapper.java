package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.DAO.RoomDAO;
import pl.paw1470.cinematac.entity.Cinema;
import pl.paw1470.cinematac.entity.Room;

import java.util.List;

public interface RoomMapper {
    Room daoToEntity(RoomDAO roomDAO, Cinema cinema);
    RoomDAO entityToDao(Room room);
    Room update(Room room, RoomDAO roomDAO);
    List<RoomDAO> listToDao(List<Room> roomList);
}
