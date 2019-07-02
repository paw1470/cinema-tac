package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.DAO.SeanceDAO;
import pl.paw1470.cinematac.entity.Movie;
import pl.paw1470.cinematac.entity.Room;
import pl.paw1470.cinematac.entity.Seance;

import java.util.List;

public interface SeanceMapper {
    Seance daoToEntity(SeanceDAO seanceDAO, Movie movie, Room room);
    SeanceDAO entityToDao(Seance seance);
    List<SeanceDAO> listToDao(List<Seance> seanceList);
}
