package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.core.model.SeanceDAO;
import pl.paw1470.cinematac.adapters.db.entity.Movie;
import pl.paw1470.cinematac.adapters.db.entity.Room;
import pl.paw1470.cinematac.adapters.db.entity.Seance;

import java.util.List;

public interface SeanceMapper {
    Seance daoToEntity(SeanceDAO seanceDAO, Movie movie, Room room);
    SeanceDAO entityToDao(Seance seance);
    List<SeanceDAO> listToDao(List<Seance> seanceList);
}
