package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.adapters.db.entity.Room;

import java.util.List;

public interface RoomMapper {
    Room daoToEntity(RoomDAO roomDAO, Cinema cinema);
    RoomDAO entityToDao(Room room);
    Room update(Room room, RoomDAO roomDAO);
    List<RoomDAO> listToDao(List<Room> roomList);

}
