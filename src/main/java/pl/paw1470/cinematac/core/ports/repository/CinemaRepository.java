package pl.paw1470.cinematac.core.ports.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.core.DAO.CinemaDAO;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;

import java.util.List;

@Repository
public interface CinemaRepository {
    Cinema getById(Long id);
    CinemaDAO getByIdDao(Long id);
    CinemaDAO add(CinemaDAO cinemaDAO);
    CinemaDAO update(CinemaDAO cinemaDAO);
    void delete(Long id);
    List<CinemaDAO> getAllCinemaDaoList();
}
