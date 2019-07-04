package pl.paw1470.cinematac.core.ports.service;

import pl.paw1470.cinematac.adapters.db.entity.Movie;
import pl.paw1470.cinematac.core.model.MovieDAO;

import java.util.List;

public interface MovieService {
    MovieDAO add(MovieDAO movieDAO);
    List<MovieDAO> getAll();
    void removeAll();
}
