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
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.service.CinemaServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class CinemaControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    private AddressMapperImpl addressMapper = new AddressMapperImpl();
    private CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();

    private AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
    private CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void create() throws Exception {
        mockMvc.perform(post("/api/cinema").content(mapper.writeValueAsString(defaultCinemaDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address.city", is("Lublin")));

    }

    @Test
    public void createWithoutAuth() throws Exception {
        mockMvc.perform(post("/api/cinema").content(mapper.writeValueAsString(defaultCinemaDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAll() throws Exception {
        cinemaService.add(defaultCinemaDAO);
        mockMvc.perform(get("/api/cinema")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address.city", is("Lublin")));
    }
}