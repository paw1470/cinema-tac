package pl.paw1470.cinematac.core.service;


import org.springframework.stereotype.Service;
import pl.paw1470.cinematac.core.model.MovieDAO;
import pl.paw1470.cinematac.core.ports.repository.MovieRepository;
import pl.paw1470.cinematac.core.ports.service.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDAO add(MovieDAO movieDAO) {
        return movieRepository.add(movieDAO);
    }

    @Override
    public List<MovieDAO> getAll() {
        return movieRepository.getAllCinemaDaoList();
    }

    @Override
    public void removeAll() {
        movieRepository.deleteAll();
    }
}
