package pl.paw1470.cinematac.adapters.maper.lite;

import pl.paw1470.cinematac.adapters.db.entity.Movie;
import pl.paw1470.cinematac.core.model.MovieDAO;
import pl.paw1470.cinematac.core.ports.mapper.MovieMapper;

import java.util.ArrayList;
import java.util.List;

public class MovieMapperImplLite implements MovieMapper {
    @Override
    public Movie daoToEntity(MovieDAO movieDAO) {
        Movie movie = new Movie(movieDAO.getTitle(),
                ""
        );
        return movie;
    }

    @Override
    public MovieDAO entityToDao(Movie movie) {
        MovieDAO movieDAO = new MovieDAO(movie.getId(),
                movie.getTitle(),
                ""
        );
        return movieDAO;
    }

    @Override
    public Movie update(Movie movie, MovieDAO movieDAO) {
        movie.setTitle(movieDAO.getTitle());
        return movie;
    }

    @Override
    public List<MovieDAO> listToDao(List<Movie> movieList) {
        List<MovieDAO> movieDAOList = new ArrayList<>();
        for (Movie m : movieList) {
            movieDAOList.add(entityToDao(m));
        }
        return movieDAOList;
    }

    public MovieDAO fastDao(String title){
        MovieDAO movieDAO = new MovieDAO(1L,
                title,
                ""
        );
        return movieDAO;
    }
}
