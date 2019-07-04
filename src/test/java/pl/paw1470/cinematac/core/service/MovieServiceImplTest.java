package pl.paw1470.cinematac.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.paw1470.cinematac.adapters.maper.lite.MovieMapperImplLite;
import pl.paw1470.cinematac.core.model.MovieDAO;
import pl.paw1470.cinematac.core.ports.mapper.MovieMapper;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @Autowired
    private MovieServiceImpl movieService;

    private MovieMapperImplLite movieMapper = new MovieMapperImplLite();

    private MovieDAO movieDAO = movieMapper.fastDao("Terminator");

    @After
    public void clearMovie(){
        movieService.removeAll();
    }

    @Test
    public void add() {
        assertTrue(movieService.getAll().isEmpty());

        movieService.add(movieDAO);

        assertFalse(movieService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        movieService.add(movieDAO);
        movieService.add(movieDAO);
        movieService.add(movieDAO);

        assertEquals(movieService.getAll().size(), 3);
    }
}