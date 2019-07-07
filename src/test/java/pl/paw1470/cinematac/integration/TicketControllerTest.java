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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.paw1470.cinematac.adapters.maper.*;
import pl.paw1470.cinematac.core.model.*;
import pl.paw1470.cinematac.core.ports.service.MovieService;
import pl.paw1470.cinematac.core.service.*;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class TicketControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
    private RoomMapperImpl roomMapper = new RoomMapperImpl();
    private SeanceMapperImpl seanceMapper = new SeanceMapperImpl();
    private MovieMapperImpl movieMapper = new MovieMapperImpl();
    private TicketMapperImpl ticketMapper = new TicketMapperImpl();

    private RoomDAO addedRoomDAO;
    private MovieDAO addedMovieDAO;
    private SeanceDAO addedSeanceDAO;

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
    private MockMvc mockMvc;

    private void addRoomMovieSeance(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        cinemaService.add(addedCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        addedRoomDAO = roomService.add(roomDAO);
        MovieDAO defaultMovieDAO = movieMapper.fastDao("Terminator");
        addedMovieDAO = movieService.add(defaultMovieDAO);
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5L);
        SeanceDAO defaultSeanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, localDateTime);
        addedSeanceDAO = seanceService.add(defaultSeanceDAO);
    }


    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void createTicket() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null || addedSeanceDAO == null) {
            addRoomMovieSeance();
        }
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        mockMvc.perform(post("/api/ticket").content(mapper.writeValueAsString(ticketDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.price", is(20.23)));
    }

    @Test
    public void getAll() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null || addedSeanceDAO == null) {
            addRoomMovieSeance();
        }
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        ticketService.removeAll();
        ticketService.add(ticketDAO);
        ticketService.add(ticketDAO);
        mockMvc.perform(get("/api/ticket").content(mapper.writeValueAsString(ticketDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].price", is(20.23)))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void getBySeance() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null || addedSeanceDAO == null) {
            addRoomMovieSeance();
        }
        TicketDAO ticketDAO = ticketMapper.fastDao(addedSeanceDAO, "normalny", 20.23);
        ticketService.removeAll();
        ticketService.add(ticketDAO);
        ticketService.add(ticketDAO);
        mockMvc.perform(get("/api/ticket/seance/"+addedSeanceDAO.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}