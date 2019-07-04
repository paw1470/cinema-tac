package pl.paw1470.cinematac.adapters.maper.lite;

import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.adapters.db.entity.Room;
import pl.paw1470.cinematac.adapters.maper.CinemaMapperImpl;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.core.ports.mapper.CinemaMapper;
import pl.paw1470.cinematac.core.ports.mapper.RoomMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomMapperImplLite implements RoomMapper {

    private CinemaMapper cinemaMapper = new CinemaMapperImplLite();

    @Override
    public Room daoToEntity(RoomDAO roomDAO, Cinema cinema) {
        Room room = new Room(cinema,
                20,
                30,
                "",
                roomDAO.getInfo());
        return room;
    }

    @Override
    public RoomDAO entityToDao(Room room) {
        RoomDAO roomDAO = new RoomDAO(room.getId(),
                cinemaMapper.entityToDao(room.getCinema()),
                20,
                30,
                "",
                room.getInfo());
        return roomDAO;
    }

    @Override
    public Room update(Room room, RoomDAO roomDAO) {
        room.setInfo(roomDAO.getInfo());
        room.setName(roomDAO.getName());
        return room;
    }

    @Override
    public List<RoomDAO> listToDao(List<Room> roomList) {
        List<RoomDAO> roomDAOList = new ArrayList<>();
        for (Room r : roomList) {
            roomDAOList.add(entityToDao(r));
        }
        return roomDAOList;
    }

    public RoomDAO fastDao(CinemaDAO cinema, String info){
        RoomDAO roomDAO = new RoomDAO(1L,
                cinema,
                20,
                30,
                "",
                info);
        return roomDAO;
    }
}
