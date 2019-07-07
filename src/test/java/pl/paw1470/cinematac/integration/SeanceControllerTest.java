package pl.paw1470.cinematac.integration;

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
import pl.paw1470.cinematac.core.service.CinemaServiceImpl;
import pl.paw1470.cinematac.core.service.MovieServiceImpl;
import pl.paw1470.cinematac.core.service.RoomServiceImpl;
import pl.paw1470.cinematac.core.service.SeanceServiceImpl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class SeanceControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
    private RoomMapperImpl roomMapper = new RoomMapperImpl();
    private SeanceMapperImpl seanceMapper = new SeanceMapperImpl();
    private MovieMapperImpl movieMapper = new MovieMapperImpl();

    private AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
    private CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
    private RoomDAO addedRoomDAO;
    private MovieDAO addedMovieDAO;

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private SeanceServiceImpl seanceService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private MockMvc mockMvc;

    private void addRoomAndMovie(){
        CinemaDAO addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
        cinemaService.add(addedCinemaDAO);
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        addedRoomDAO = roomService.add(roomDAO);
        MovieDAO defaultMovieDAO = movieMapper.fastDao("Terminator");
        addedMovieDAO = movieService.add(defaultMovieDAO);
    }

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void createSeance() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null) {
            addRoomAndMovie();
        }
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5L);
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, localDateTime);
        mockMvc.perform(post("/api/seance").content(mapper.writeValueAsString(seanceDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.seanceDate", is(localDateTime.toString())));
    }

    @Test
    public void getAll() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null) {
            addRoomAndMovie();
        }
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5L);
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, localDateTime);
        mockMvc.perform(get("/api/seance").content(mapper.writeValueAsString(seanceDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getByCity() throws Exception {
        if (addedRoomDAO == null || addedMovieDAO == null) {
            addRoomAndMovie();
        }
        LocalDateTime localDateTime =  LocalDateTime.now().plusDays(5L);
        SeanceDAO seanceDAO = seanceMapper.fastDao(addedRoomDAO, addedMovieDAO, localDateTime);
        seanceService.add(seanceDAO);
        mockMvc.perform(get("/api/seance/cinema/"+addedRoomDAO.getCinema().getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}