package pl.paw1470.cinematac.core.service;

import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.ReservationDAO;
import pl.paw1470.cinematac.core.ports.repository.ReservationRepository;
import pl.paw1470.cinematac.core.ports.service.ReservationService;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<ReservationDAO> add(ReservationDAO reservationDAO) {
        return reservationRepository.add(reservationDAO);
    }

    @Override
    public Optional<ReservationDAO> getById(Long id) {
        return reservationRepository.getByIdDao(id);
    }

    @Override
    public List<ReservationDAO> getAll() {
        return reservationRepository.getAll();
    }

    @Override
    public List<ReservationDAO> getBySeance(Long id) {
        return reservationRepository.getAllBySeance(id);
    }

    @Override
    public void removeById(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public boolean[][] getFreePlacesBySeance(Long id) {
        List<ReservationDAO> reservationDAOList = getBySeance(id);
        int row = 20;
        int column = 30;
        boolean[][] freePlaces = new boolean[column][row];
        for(int c = 0; c < column; c++) {
            for (int r = 0; r < row; r++) {
                freePlaces[c][r] = true;
            }
        }
        if (reservationDAOList.isEmpty()){
        } else {
            for (ReservationDAO reservation : reservationDAOList) {
                freePlaces[reservation.getColumn()][reservation.getRow()] = false;
            }
        }
        return freePlaces;
    }

    @Override
    public void confirmReservation(Long id) {
        reservationRepository.confirm(id);
    }

    @Override
    public void removeAll() {
        reservationRepository.deleteAll();
    }

    @Override
    public boolean isPlaceFree(ReservationDAO reservationDAO) {
        return reservationRepository.isPlaceFree(reservationDAO);
    }

    @Override
    public void removeNotConfirmed(Long id) {
        reservationRepository.deleteNotConfirmedBySeance(id);
    }

}
