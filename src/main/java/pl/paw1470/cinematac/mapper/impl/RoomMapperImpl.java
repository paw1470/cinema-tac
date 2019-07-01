package pl.paw1470.cinematac.mapper.impl;

import pl.paw1470.cinematac.DAO.RoomDAO;
import pl.paw1470.cinematac.entity.Cinema;
import pl.paw1470.cinematac.entity.Room;
import pl.paw1470.cinematac.mapper.CinemaMapper;
import pl.paw1470.cinematac.mapper.RoomMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomMapperImpl implements RoomMapper {

    private CinemaMapper cinemaMapper = new CinemaMapperImpl();

    @Override
    public Room daoToEntity(RoomDAO roomDAO, Cinema cinema) {
        Room room = new Room(cinema,
                roomDAO.getRows(),
                roomDAO.getColumns(),
                roomDAO.getName(),
                roomDAO.getInfo());
        return room;
    }

    @Override
    public RoomDAO entityToDao(Room room) {
        RoomDAO roomDAO = new RoomDAO(room.getId(),
                cinemaMapper.entityToDao(room.getCinema()),
                room.getRows(),
                room.getColumns(),
                room.getName(),
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
}
