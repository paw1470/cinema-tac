package pl.paw1470.cinematac.adapters.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.adapters.db.entity.Cinema;
import pl.paw1470.cinematac.core.ports.mapper.CinemaMapper;
import pl.paw1470.cinematac.adapters.maper.CinemaMapperImpl;
import pl.paw1470.cinematac.core.ports.repository.AddressRepository;
import pl.paw1470.cinematac.core.ports.repository.CinemaRepository;
import pl.paw1470.cinematac.core.DAO.CinemaDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CinemaRepositoryImpl implements CinemaRepository {

    private CinemaMapper cinemaMapper = new CinemaMapperImpl();

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Cinema getById(Long id) {
        return entityManager.find(Cinema.class, id);
    }

    @Override
    public CinemaDAO getByIdDao(Long id) {
        Cinema cinema = getById(id);
        return cinemaMapper.entityToDao(cinema);
    }

    @Override
    public CinemaDAO add(CinemaDAO cinemaDAO) {
        Address address = addressRepository.add(cinemaDAO.getAddress());
        Cinema cinema = cinemaMapper.daoToEntity(cinemaDAO, address);
        entityManager.persist(cinema);
        return cinemaMapper.entityToDao(cinema);
    }

    @Override
    public CinemaDAO update(CinemaDAO cinemaDAO) {
        Cinema cinema = getById(cinemaDAO.getId());
        cinemaMapper.update(cinema, cinemaDAO);
        entityManager.flush();
        return getByIdDao(cinemaDAO.getId());    //todo czy wyszukiwanie jeszcze raz ma sens jak i tak dane identyczne?
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public List<CinemaDAO> getAllCinemaDaoList() {
        Query query = entityManager.createQuery("FROM cinema");
        List<Cinema> cinemaList = query.getResultList();
        return cinemaMapper.listToDao(cinemaList);
    }
}
