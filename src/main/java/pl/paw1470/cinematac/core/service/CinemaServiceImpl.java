package pl.paw1470.cinematac.core.service;

import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.ports.repository.CinemaRepository;
import pl.paw1470.cinematac.core.ports.service.CinemaService;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    private CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository){
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public CinemaDAO add(CinemaDAO cinemaDAO) {
        return cinemaRepository.add(cinemaDAO);
    }

    @Override
    public List<CinemaDAO> getAll() {
        return cinemaRepository.getAllCinemaDaoList();
    }

    @Override
    public List<CinemaDAO> getByCity(String city) {
        return cinemaRepository.getAllCinemaByCityDaoList(city);
    }

    @Override
    public void removeAll() {
        cinemaRepository.deleteAll();
    }
}
