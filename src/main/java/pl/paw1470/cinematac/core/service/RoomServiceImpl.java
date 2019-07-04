package pl.paw1470.cinematac.core.service;

import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.core.ports.repository.RoomRepository;
import pl.paw1470.cinematac.core.ports.service.RoomService;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDAO add(RoomDAO roomDAO) {
        return roomRepository.add(roomDAO);
    }

    @Override
    public List<RoomDAO> getAll() {
        return roomRepository.getAllRoomDaoList();
    }

    @Override
    public List<RoomDAO> getByCinema(Long id) {
        return roomRepository.getAllRoomByCinemaList(id);
    }

    @Override
    public void removeAll() {
        roomRepository.deleteAll();
    }
}
