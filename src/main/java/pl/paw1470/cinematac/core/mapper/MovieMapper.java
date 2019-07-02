package pl.paw1470.cinematac.core.mapper;

import pl.paw1470.cinematac.core.entity.Movie;
import pl.paw1470.cinematac.core.DAO.MovieDAO;

import java.util.List;

public interface MovieMapper {
    Movie daoToEntity(MovieDAO movieDAO);
    MovieDAO entityToDao(Movie movie);
    Movie update(Movie movie, MovieDAO movieDAO);
    List<MovieDAO> listToDao(List<Movie> movieList);
}
