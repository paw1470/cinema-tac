package pl.paw1470.cinematac.core.ports.service;

import pl.paw1470.cinematac.core.model.CinemaDAO;

import java.util.List;

public interface CinemaService {
    CinemaDAO add(CinemaDAO cinemaDAO);
    List<CinemaDAO> getAll();
    List<CinemaDAO> getByCity(String city);
    void removeAll();
}
