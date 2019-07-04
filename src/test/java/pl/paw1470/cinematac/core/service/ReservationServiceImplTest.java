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
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationServiceImplTest {

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private SeanceServiceImpl seanceService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private TicketServiceImpl ticketService;

    private CinemaMapperImplLite cinemaMapper = new CinemaMapperImplLite();
    private AddressMapperImplLite addressMapper = new AddressMapperImplLite();
    private RoomMapperImplLite roomMapper = new RoomMapperImplLite();
    private SeanceMapperImplLite seanceMapper = new SeanceMapperImplLite();
    private MovieMapperImplLite movieMapper = new MovieMapperImplLite();
    private ReservationMapperImplLite reservationMapper = new ReservationMapperImplLite();
    private TicketMapperImplLite ticketMapper = new TicketMapperImplLite();

    private SeanceDAO addedSeanceDAO;
    private TicketDAO addedTicketDAO_1;
    private TicketDAO addedTicketDAO_2;

    @After
    public void clearReservation(){
        reservationService.removeAll();
        ticketService.removeAll();
        seanceService.removeAll();
        roomService.removeAll();
        cinemaService.removeAll();
        movieService.removeAll();
    }

    @Before
    public void prepareForReservation(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        RoomDAO addedRoomDAO = roomService.add(roomDAO);

        MovieDAO movieDAO = movieMapper.fastDao("T");
        MovieDAO addedMovieDAO = movieService.add(movieDAO);

        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, new Date(2019, 10, 10));
        addedSeanceDAO = seanceService.add(seanceDAO);

        TicketDAO ticketDAO_1 = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        addedTicketDAO_1 = ticketService.add(ticketDAO_1);

        TicketDAO ticketDAO_2 = ticketMapper.fastDao(addedSeanceDAO, "dla dzieciaka", 100.52);
        addedTicketDAO_1 = ticketService.add(ticketDAO_1);
    }

    @Test
    public void add() {
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 2, 4);
        assertTrue(reservationService.isPlaceFree(reservationDAO));
        assertEquals(reservationService.getBySeance(addedSeanceDAO.getId()).size(), 0);

        Optional<ReservationDAO> added_1 = reservationService.add(reservationDAO);
        assertTrue(added_1.isPresent());

        assertEquals(reservationService.getBySeance(addedSeanceDAO.getId()).size(), 1);
        assertFalse(reservationService.isPlaceFree(reservationDAO));

        Optional<ReservationDAO> added_2 = reservationService.add(reservationDAO);
        assertFalse(added_2.isPresent());

        assertFalse(reservationService.isPlaceFree(reservationDAO));
    }

    @Test
    public void getById() {
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 2, 4);
        assertFalse(reservationService.getById(100L).isPresent());
        Optional<ReservationDAO> added = reservationService.add(reservationDAO);
        assertTrue(reservationService.getById(added.get().getId()).isPresent());
    }

    @Test
    public void getAll() {
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 2, 4);
        reservationService.add(reservationDAO);
        ReservationDAO reservationDAO2 = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 4, 4);
        reservationService.add(reservationDAO2);
        assertEquals(reservationService.getBySeance(addedSeanceDAO.getId()).size(), 2);
    }

    @Test
    public void getBySeance() {
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 2, 4);
        reservationService.add(reservationDAO);
        ReservationDAO reservationDAO2 = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 4, 4);
        reservationService.add(reservationDAO2);
        assertEquals(reservationService.getBySeance(addedSeanceDAO.getId()).size(), 2);
    }

    @Test
    public void getFreePlacesBySeance() {
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_1, false, "Jan Marian", 2, 4);
        boolean[][] places = reservationService.getFreePlacesBySeance(addedSeanceDAO.getId());
        assertTrue(places[reservationDAO.getColumn()][reservationDAO.getRow()]);
        assertTrue(reservationService.isPlaceFree(reservationDAO));
        reservationService.add(reservationDAO);

        places = reservationService.getFreePlacesBySeance(addedSeanceDAO.getId());
        assertFalse(places[reservationDAO.getColumn()][reservationDAO.getRow()]);
        assertFalse(reservationService.isPlaceFree(reservationDAO));
    }
}