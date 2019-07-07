package pl.paw1470.cinematac.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.paw1470.cinematac.adapters.maper.*;
import pl.paw1470.cinematac.core.model.*;
import pl.paw1470.cinematac.core.service.*;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class ReservationControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
    private RoomMapperImpl roomMapper = new RoomMapperImpl();
    private SeanceMapperImpl seanceMapper = new SeanceMapperImpl();
    private MovieMapperImpl movieMapper = new MovieMapperImpl();
    private TicketMapperImpl ticketMapper = new TicketMapperImpl();
    private ReservationMapperImpl reservationMapper = new ReservationMapperImpl();


    private SeanceDAO addedSeanceDAO;
    private TicketDAO addedTicketDAO_1;
    private TicketDAO addedTicketDAO_2;


    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private SeanceServiceImpl seanceService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private MockMvc mockMvc;

    private void addRoomMovieSeanceTicket(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        cinemaService.add(addedCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        RoomDAO addedRoomDAO = roomService.add(roomDAO);
        MovieDAO defaultMovieDAO = movieMapper.fastDao("Terminator");
        MovieDAO addedMovieDAO = movieService.add(defaultMovieDAO);
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5L);
        SeanceDAO defaultSeanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, localDateTime);
        addedSeanceDAO = seanceService.add(defaultSeanceDAO);
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        addedTicketDAO_1 = ticketService.add(ticketDAO);
        TicketDAO ticketDAO2 = ticketMapper.fastDao(addedSeanceDAO, "studencki", 10.50);
        addedTicketDAO_2 = ticketService.add(ticketDAO2);
    }

    @Test
    public void cancel() throws Exception {
        if (addedTicketDAO_1 == null || addedTicketDAO_2 == null || addedSeanceDAO == null) {
            addRoomMovieSeanceTicket();
        }
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_2, false, "Jan Kowalski", 2,5);
        reservationService.removeAll();
        Optional<ReservationDAO> addedReservationDAO = reservationService.add(reservationDAO);
        mockMvc.perform(post("/api/reservation/" + addedReservationDAO.get().getId() + "/cancel")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    public void createReservation() throws Exception {
        if (addedTicketDAO_1 == null || addedTicketDAO_2 == null || addedSeanceDAO == null) {
            addRoomMovieSeanceTicket();
        }
        ReservationDAO reservationDAO = reservationMapper.fastDao(addedSeanceDAO, addedTicketDAO_2, false, "Jan Kowalski", 2,5);
        reservationService.removeAll();
        mockMvc.perform(post("/api/reservation/reserve").content(mapper.writeValueAsString(reservationDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void confirm() {}


}