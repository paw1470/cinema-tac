package pl.paw1470.cinematac.core.service;

import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.core.ports.repository.SeanceRepository;
import pl.paw1470.cinematac.core.ports.service.SeanceService;

import java.util.List;

@Service
public class SeanceServiceImpl implements SeanceService {

    private SeanceRepository seanceRepository;

    public SeanceServiceImpl(SeanceRepository seanceRepository){
        this.seanceRepository = seanceRepository;
    }

    @Override
    public SeanceDAO add(SeanceDAO seanceDAO) {
        return seanceRepository.add(seanceDAO);
    }

    @Override
    public List<SeanceDAO> getAll() {
        return seanceRepository.getAllSeanceDaoList();
    }



    @Override
    public List<SeanceDAO> getAllByCinema(Long id) {
        return seanceRepository.getAllSeanceByCinemaDaoList(id);
    }

    @Override
    public SeanceDAO setTicketAvailability(Long id, boolean availability) {     //czy mozna kupic jakies bilety
        return  seanceRepository.setTicketAvailability(id, availability);
    }

    @Override
    public SeanceDAO setReservationAvailability(Long id, boolean availability) {    //czy mozna rezerwowac bilety na seans
        return seanceRepository.setReservationAvailability(id, availability);       //po wylaczeniu usuwa wszystkie nieoplacone rezerwacje
    }

    @Override
    public void removeAll() {
        seanceRepository.deleteAll();
    }
}
