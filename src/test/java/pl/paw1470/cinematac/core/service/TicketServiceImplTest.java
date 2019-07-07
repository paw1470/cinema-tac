package pl.paw1470.cinematac.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.paw1470.cinematac.adapters.maper.*;
import pl.paw1470.cinematac.core.model.*;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceImplTest {

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private SeanceServiceImpl seanceService;

    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private RoomMapperImpl roomMapper = new RoomMapperImpl();
    private SeanceMapperImpl seanceMapper = new SeanceMapperImpl();
    private MovieMapperImpl movieMapper = new MovieMapperImpl();
    private TicketMapperImpl ticketMapper = new TicketMapperImpl();

    private SeanceDAO addedSeanceDAO;

    @After
    public void clearTicket(){
        ticketService.removeAll();
        seanceService.removeAll();
        roomService.removeAll();
        cinemaService.removeAll();
        movieService.removeAll();
    }

    @Before
    public void prepareForTicket(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        RoomDAO addedRoomDAO = roomService.add(roomDAO);

        MovieDAO movieDAO = movieMapper.fastDao("T");
        MovieDAO addedMovieDAO = movieService.add(movieDAO);

        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, LocalDateTime.now().plusDays(5L));
        addedSeanceDAO = seanceService.add(seanceDAO);
    }

    @Test
    public void add() {
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        assertTrue(ticketService.getAll().isEmpty());

        ticketService.add(ticketDAO);

        assertFalse(seanceService.getAll().isEmpty());
    }

    @Test
    public void getAll() {
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        ticketService.add(ticketDAO);
        ticketService.add(ticketDAO);
        ticketService.add(ticketDAO);
        assertEquals(ticketService.getAll().size(), 3);
    }

    @Test
    public void getByMovie() {
        //czy ja tego bede uzywal???
//        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
//        ticketService.add(ticketDAO);
//        assertEquals(ticketService.getByMovie(addedSeanceDAO.getMovie().getId()).size(), 1);

    }

    @Test
    public void getBySeance() {
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        ticketService.add(ticketDAO);
        assertEquals(ticketService.getBySeance(addedSeanceDAO.getId()).size(), 1);
        assertEquals(ticketService.getBySeance(addedSeanceDAO.getId() + 100L).size(), 0);
    }
}