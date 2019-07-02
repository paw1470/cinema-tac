package pl.paw1470.cinematac.core.mapper;

import pl.paw1470.cinematac.core.DAO.SeanceDAO;
import pl.paw1470.cinematac.core.entity.Movie;
import pl.paw1470.cinematac.core.entity.Room;
import pl.paw1470.cinematac.core.entity.Seance;

import java.util.List;

public interface SeanceMapper {
    Seance daoToEntity(SeanceDAO seanceDAO, Movie movie, Room room);
    SeanceDAO entityToDao(Seance seance);
    List<SeanceDAO> listToDao(List<Seance> seanceList);
}
