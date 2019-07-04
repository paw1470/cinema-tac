package pl.paw1470.cinematac.adapters.maper;

import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.core.ports.mapper.AddressMapper;
import pl.paw1470.cinematac.core.ports.mapper.CinemaMapper;
import pl.paw1470.cinematac.core.model.CinemaDAO;

import java.util.ArrayList;
import java.util.List;

public class CinemaMapperImpl implements CinemaMapper {

    private AddressMapper addressMapper = new AddressMapperImpl();

    @Override
    public Cinema daoToEntity(CinemaDAO cinemaDAO, Address address) {
        Cinema cinema = new Cinema(cinemaDAO.getName(),
                address,
                cinemaDAO.getEmail(),
                cinemaDAO.getTel(),
                cinemaDAO.getInfo());
        return cinema;
    }

    @Override
    public CinemaDAO entityToDao(Cinema cinema) {
        CinemaDAO cinemaDAO = new CinemaDAO(cinema.getId(),
                cinema.getName(),
                addressMapper.entityToDao(cinema.getAddress()),
                cinema.getEmail(),
                cinema.getTel(),
                cinema.getInfo());
        return cinemaDAO;
    }

    @Override
    public Cinema update(Cinema cinemaEntity, CinemaDAO cinemaDAO) {
        cinemaEntity.setName(cinemaDAO.getName());
        cinemaEntity.setEmail(cinemaDAO.getEmail());
        cinemaEntity.setInfo(cinemaDAO.getInfo());
        cinemaEntity.setTel(cinemaDAO.getTel());
        return cinemaEntity;
    }

    @Override
    public List<CinemaDAO> listToDao(List<Cinema> cinemaList) {
        List<CinemaDAO> cinemaDAOList = new ArrayList<>();
        if(cinemaList.isEmpty()){
        } else {
            for (Cinema c : cinemaList) {
                cinemaDAOList.add(entityToDao(c));
            }
        }
        return cinemaDAOList;
    }
}
