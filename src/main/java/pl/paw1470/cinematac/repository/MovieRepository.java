package pl.paw1470.cinematac.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.DAO.MovieDAO;

import java.util.List;

@Repository
public interface MovieRepository {
    MovieDAO getByIdDao(Long id);
    MovieDAO add(MovieDAO movieDAO);
    MovieDAO update(MovieDAO movieDAO);
    void delete(Long id);
    List<MovieDAO> getAllCinemaDaoList();
}
