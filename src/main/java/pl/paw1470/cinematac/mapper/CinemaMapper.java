package pl.paw1470.cinematac.mapper;


import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.entity.Cinema;
import pl.paw1470.cinematac.DAO.CinemaDAO;

import java.util.List;

public interface CinemaMapper {
    Cinema daoToEntity(CinemaDAO cinemaDAO, Address address);
    CinemaDAO entityToDao(Cinema cinema);
    Cinema update(Cinema cinemaEntity, CinemaDAO cinemaDAO);
    List<CinemaDAO> listToDao(List<Cinema> cinemaList);
}
