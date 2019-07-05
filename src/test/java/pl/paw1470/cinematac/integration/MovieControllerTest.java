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
import pl.paw1470.cinematac.adapters.maper.lite.MovieMapperImplLite;
import pl.paw1470.cinematac.core.model.MovieDAO;
import pl.paw1470.cinematac.core.ports.service.MovieService;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@AutoConfigureMockMvc
public class MovieControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    private MovieMapperImplLite movieMapper = new MovieMapperImplLite();

    private MovieDAO defaultMovieDAO = movieMapper.fastDao("Terminator");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @Test
    @WithMockUser(username = "test", roles = {"ADMIN"})
    public void create() throws Exception {
        mockMvc.perform(post("/api/movie").content(mapper.writeValueAsString(defaultMovieDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Terminator")));
    }

    @Test
    public void createWithoutAuth() throws Exception {
        mockMvc.perform(post("/api/movie").content(mapper.writeValueAsString(defaultMovieDAO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAll() throws Exception {
        movieService.add(defaultMovieDAO);
        mockMvc.perform(get("/api/movie")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Terminator")));
    }
}