package pl.paw1470.cinematac.core.ports.service;

import pl.paw1470.cinematac.core.model.RoomDAO;

import java.util.List;

public interface RoomService {
    RoomDAO add(RoomDAO roomDAO);
    List<RoomDAO> getAll();
    List<RoomDAO> getByCinema(Long id);
    void removeAll();
}
