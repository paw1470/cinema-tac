package pl.paw1470.cinematac.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.paw1470.cinematac.adapters.maper.lite.*;
import pl.paw1470.cinematac.core.model.*;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeanceServiceImplTest {

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private SeanceServiceImpl seanceService;

    private CinemaMapperImplLite cinemaMapper = new CinemaMapperImplLite();
    private AddressMapperImplLite addressMapper = new AddressMapperImplLite();
    private RoomMapperImplLite roomMapper = new RoomMapperImplLite();
    private SeanceMapperImplLite seanceMapper = new SeanceMapperImplLite();
    private MovieMapperImplLite movieMapperImplLite = new MovieMapperImplLite();

    private RoomDAO addedRoomDAO;
    private MovieDAO addedMovieDAO;

    @After
    public void clearSeance(){
        seanceService.removeAll();
        roomService.removeAll();
        cinemaService.removeAll();
        movieService.removeAll();
    }

     @Before
     public void PrepareForSeance(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        addedRoomDAO = roomService.add(roomDAO);
        MovieDAO movieDAO = movieMapperImplLite.fastDao("T");
        addedMovieDAO = movieService.add(movieDAO);
    }

    @Test
    public void add() {
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        assertTrue(seanceService.getAll().isEmpty());

        seanceService.add(seanceDAO);

        assertFalse(seanceService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        seanceService.add(seanceDAO);
        seanceService.add(seanceDAO);
        seanceService.add(seanceDAO);

        assertEquals(seanceService.getAll().size(), 3);
    }

    @Test
    public void getAllByCinema() {
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        seanceService.add(seanceDAO);
        assertEquals(seanceService.getAllByCinema(addedRoomDAO.getCinema().getId()).size(), 1);
        assertEquals(seanceService.getAllByCinema(addedRoomDAO.getCinema().getId()+ 20L).size() , 0);

    }

    @Test
    public void setTicketAvailability() {
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        SeanceDAO addedSeanceDAO = seanceService.add(seanceDAO);
        assertTrue(addedSeanceDAO.isTicketsAvailable());
        addedSeanceDAO = seanceService.setTicketAvailability(addedSeanceDAO.getId(), false);
        assertFalse(addedSeanceDAO.isTicketsAvailable());
        addedSeanceDAO = seanceService.setTicketAvailability(addedSeanceDAO.getId(), true);
        assertTrue(addedSeanceDAO.isTicketsAvailable());
    }

    @Test
    public void setReservationAvailability() {
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        SeanceDAO addedSeanceDAO = seanceService.add(seanceDAO);
        assertTrue(addedSeanceDAO.isReservationAvailable());
        addedSeanceDAO = seanceService.setReservationAvailability(addedSeanceDAO.getId(), false);
        assertFalse(addedSeanceDAO.isReservationAvailable());
        addedSeanceDAO = seanceService.setReservationAvailability(addedSeanceDAO.getId(), true);
        assertTrue(addedSeanceDAO.isReservationAvailable());
    }
}