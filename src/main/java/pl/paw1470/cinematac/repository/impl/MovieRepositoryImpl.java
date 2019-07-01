package pl.paw1470.cinematac.repository.impl;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.entity.Movie;
import pl.paw1470.cinematac.mapper.MovieMapper;
import pl.paw1470.cinematac.mapper.impl.MovieMapperImpl;
import pl.paw1470.cinematac.repository.MovieRepository;
import pl.paw1470.cinematac.DAO.MovieDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepository {

    private MovieMapper movieMapper = new MovieMapperImpl();

    @PersistenceContext
    private EntityManager entityManager;

    protected Movie getById(Long id){
        return entityManager.find(Movie.class, id);
    }

    @Override
    public MovieDAO getByIdDao(Long id) {
        Movie movie = getById(id);
        return movieMapper.entityToDao(movie);
    }

    @Override
    public MovieDAO add(MovieDAO movieDAO) {
        Movie movie = movieMapper.daoToEntity(movieDAO);
        entityManager.persist(movie);
        return movieMapper.entityToDao(movie);
    }

    @Override
    public MovieDAO update(MovieDAO movieDAO) {
        Movie movie = getById(movieDAO.getId());
        movieMapper.update(movie, movieDAO);
        entityManager.flush();
        return getByIdDao(movieDAO.getId());  //todo czy wyszukiwanie jeszcze raz ma sens jak i tak dane identyczne?
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<MovieDAO> getAllCinemaDaoList() {
        Query query = entityManager.createQuery("FROM movie");
        List<Movie> movieList = query.getResultList();
        return movieMapper.listToDao((movieList));
    }
}
