package pl.paw1470.cinematac.adapters.maper.lite;

import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.adapters.maper.AddressMapperImpl;
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.model.CinemaDAO;
import pl.paw1470.cinematac.core.ports.mapper.AddressMapper;
import pl.paw1470.cinematac.core.ports.mapper.CinemaMapper;

import java.util.ArrayList;
import java.util.List;

public class CinemaMapperImplLite implements CinemaMapper {

    private AddressMapper addressMapper = new AddressMapperImplLite();

    @Override
    public Cinema daoToEntity(CinemaDAO cinemaDAO, Address address) {
        Cinema cinema = new Cinema(cinemaDAO.getName(),
                address,
                "",
                "",
                cinemaDAO.getInfo()
        );
        return cinema;
    }

    @Override
    public CinemaDAO entityToDao(Cinema cinema) {
        CinemaDAO cinemaDAO = new CinemaDAO(cinema.getId(),
                cinema.getName(),
                addressMapper.entityToDao(cinema.getAddress()),
                "",
                "",
                cinema.getInfo()
        );
        return cinemaDAO;
    }

    @Override
    public Cinema update(Cinema cinemaEntity, CinemaDAO cinemaDAO) {
//        cinemaEntity.setName(cinemaDAO.getName());
//        cinemaEntity.setEmail(cinemaDAO.getEmail());
//        cinemaEntity.setInfo(cinemaDAO.getInfo());
//        cinemaEntity.setTel(cinemaDAO.getTel());
        return cinemaEntity;
    }

    @Override
    public List<CinemaDAO> listToDao(List<Cinema> cinemaList) {
        List<CinemaDAO> cinemaDAOList = new ArrayList<>();
        for (Cinema c : cinemaList) {
            cinemaDAOList.add(entityToDao(c));
        }
        return cinemaDAOList;
    }

    public CinemaDAO fastDao(String name, String info, AddressDAO address){
        CinemaDAO cinemaDAO = new CinemaDAO(1L,
                name,
                address,
                "",
                "",
                name
                );
        return cinemaDAO;
    }
}
