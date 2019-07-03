package pl.paw1470.cinematac.adapters.maper;

import pl.paw1470.cinematac.core.DAO.SeanceDAO;
import pl.paw1470.cinematac.adapters.db.entity.Movie;
import pl.paw1470.cinematac.adapters.db.entity.Room;
import pl.paw1470.cinematac.adapters.db.entity.Seance;
import pl.paw1470.cinematac.core.ports.mapper.MovieMapper;
import pl.paw1470.cinematac.core.ports.mapper.RoomMapper;
import pl.paw1470.cinematac.core.ports.mapper.SeanceMapper;

import java.util.ArrayList;
import java.util.List;

public class SeanceMapperImpl implements SeanceMapper {

    private MovieMapper movieMapper = new MovieMapperImpl();
    private RoomMapper roomMapper = new RoomMapperImpl();

    @Override
    public Seance daoToEntity(SeanceDAO seanceDAO, Movie movie, Room room) {
        Seance seance = new Seance(room,
                seanceDAO.getSeansDate(),
                movie,
                seanceDAO.isReservationAvailable(),
                seanceDAO.isTicketsAvailable());
        return seance;
    }

    @Override
    public SeanceDAO entityToDao(Seance seance) {
        SeanceDAO seanceDAO = new SeanceDAO(seance.getId(),
                roomMapper.entityToDao(seance.getRoom()),
                seance.getSeanceDate(),
                movieMapper.entityToDao(seance.getMovie()),
                seance.isReservationAvailable(),
                seance.isTicketsAvailable());
        return seanceDAO;
    }

    @Override
    public List<SeanceDAO> listToDao(List<Seance> seanceList) {
        List<SeanceDAO> seanceDAOS = new ArrayList<>();
        for (Seance s : seanceList) {
            seanceDAOS.add(entityToDao(s));
        }
        return seanceDAOS;
    }
}
