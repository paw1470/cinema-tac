package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.entity.Movie;
import pl.paw1470.cinematac.DAO.MovieDAO;

import java.util.List;

public interface MovieMapper {
    Movie daoToEntity(MovieDAO movieDAO);
    MovieDAO entityToDao(Movie movie);
    Movie update(Movie movie, MovieDAO movieDAO);
    List<MovieDAO> listToDao(List<Movie> movieList);
}
