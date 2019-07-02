package pl.paw1470.cinematac.repository.impl;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.mapper.AddressMapper;
import pl.paw1470.cinematac.mapper.impl.AddressMapperImpl;
import pl.paw1470.cinematac.repository.AddressRepository;
import pl.paw1470.cinematac.DAO.AddressDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

    private AddressMapper addressMapper = new AddressMapperImpl();

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address getById(Long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public AddressDAO getByIdDao(Long id) {
        return addressMapper.entityToDao(getById(id));
    }

    @Override
    public Address add(AddressDAO addressDAO) {
        Address address = addressMapper.daoToEntity(addressDAO);
        entityManager.persist(address);
        return address;
    }

    //update nie ma bo mysle ze lepiej usunac i dac nowy adres

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }
}
