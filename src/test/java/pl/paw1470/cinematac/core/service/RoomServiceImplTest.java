package pl.paw1470.cinematac.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.paw1470.cinematac.adapters.maper.lite.AddressMapperImplLite;
import pl.paw1470.cinematac.adapters.maper.lite.CinemaMapperImplLite;
import pl.paw1470.cinematac.adapters.maper.lite.RoomMapperImplLite;
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.core.ports.service.RoomService;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomServiceImplTest {

    @Autowired
    private CinemaServiceImpl cinemaService;

    @Autowired
    private RoomServiceImpl roomService;

    private CinemaMapperImplLite cinemaMapper = new CinemaMapperImplLite();
    private AddressMapperImplLite addressMapper = new AddressMapperImplLite();
    private RoomMapperImplLite roomMapper = new RoomMapperImplLite();


    private CinemaDAO addedCinemaDAO;

    @After
    public void clearRoom(){
        roomService.removeAll();
        cinemaService.removeAll();
    }

    @Before
    public void prepareForRoom(){
        AddressDAO defaultAddressDAO = addressMapper.fastDao("Lublin");
        CinemaDAO defaultCinemaDAO = cinemaMapper.fastDao("Cinema", "info", defaultAddressDAO);
        addedCinemaDAO = cinemaService.add(defaultCinemaDAO);
    }


    @Test
    public void add() {
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");

        assertTrue(roomService.getAll().isEmpty());

        roomService.add(roomDAO);

        assertFalse(roomService.getAll().isEmpty());

    }

    @Test
    public void getAll() {
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        roomService.add(roomDAO);
        roomService.add(roomDAO);
        roomService.add(roomDAO);

        assertEquals(roomService.getAll().size(), 3);

    }

    @Test
    public void getByCinema() {
        RoomDAO roomDAO = roomMapper.fastDao(addedCinemaDAO, "Glowny");
        roomService.add(roomDAO);

        assertEquals(roomService.getByCinema(addedCinemaDAO.getId()).size(), 1);
        assertEquals(roomService.getByCinema(addedCinemaDAO.getId() + 20L).size(), 0);
    }
}