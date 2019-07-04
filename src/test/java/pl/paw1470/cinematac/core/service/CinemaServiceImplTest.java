package pl.paw1470.cinematac.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.paw1470.cinematac.adapters.maper.lite.AddressMapperImplLite;
import pl.paw1470.cinematac.adapters.maper.lite.CinemaMapperImplLite;
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.model.CinemaDAO;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaServiceImplTest {

    @Autowired
    private CinemaServiceImpl cinemaService;

    private CinemaMapperImplLite cinemaMapper = new CinemaMapperImplLite();
    private AddressMapperImplLite addressMapper = new AddressMapperImplLite();

    private AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
    private CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);

    @After
    public void clearCinema(){

        cinemaService.removeAll();
    }

    @Test
    public void add() {
        assertTrue(cinemaService.getAll().isEmpty());

        cinemaService.add(defaultCinemaDAO);

        assertFalse(cinemaService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        cinemaService.add(defaultCinemaDAO);
        cinemaService.add(defaultCinemaDAO);
        cinemaService.add(defaultCinemaDAO);

        assertEquals(cinemaService.getAll().size(), 3);
    }

    @Test
    public void getByCity() {
        cinemaService.add(defaultCinemaDAO);

        assertEquals(cinemaService.getByCity("Lublin").size(), 1);
        assertEquals(cinemaService.getByCity("Warszawa").size(), 0);
    }
}