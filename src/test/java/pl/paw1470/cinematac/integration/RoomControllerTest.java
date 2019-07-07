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
import pl.paw1470.cinematac.adapters.maper.AddressMapperImpl;
import pl.paw1470.cinematac.adapters.maper.CinemaMapperImpl;
import pl.paw1470.cinematac.adapters.maper.RoomMapperImpl;
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.core.service.CinemaServiceImpl;
import pl.paw1470.cinematac.core.service.RoomServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class RoomControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
    private RoomMapperImpl roomMapper = new RoomMapperImpl();

    private AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
    private CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
    private CinemaDAO addedCinemaDao;

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private MockMvc mockMvc;

    private void addCinema(){
        addedCinemaDao = cinemaService.add(defaultCinemaDAO);
    }

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void create() throws Exception {
        if (addedCinemaDao == null) {
            addCinema();
        }
        RoomDAO defaultRoomDAO = roomMapper.fastDao(addedCinemaDao, "Duzy");
        mockMvc.perform(post("/api/room").content(mapper.writeValueAsString(defaultRoomDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.info", is("Duzy")));
    }

    @Test
    public void createWithoutAuth() throws Exception {
        if (addedCinemaDao == null) {
            addCinema();
        }
        RoomDAO defaultRoomDAO = roomMapper.fastDao(addedCinemaDao, "Duzy");
        mockMvc.perform(post("/api/room").content(mapper.writeValueAsString(addedCinemaDao))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAll() throws Exception {
        if (addedCinemaDao == null) {
            addCinema();
        }
        RoomDAO defaultRoomDAO = roomMapper.fastDao(addedCinemaDao, "Duzy");
        roomService.add(defaultRoomDAO);
        mockMvc.perform(get("/api/room")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}