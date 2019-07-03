package pl.paw1470.cinematac.core.ports.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.core.DAO.MovieDAO;
import pl.paw1470.cinematac.adapters.db.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository {
    Movie getById(Long id);
    MovieDAO getByIdDao(Long id);
    MovieDAO add(MovieDAO movieDAO);
    MovieDAO update(MovieDAO movieDAO);
    void delete(Long id);
    List<MovieDAO> getAllCinemaDaoList();
}
