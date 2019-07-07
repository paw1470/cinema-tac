package pl.paw1470.cinematac.adapters.db.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.adapters.maper.AddressMapperImpl;
import pl.paw1470.cinematac.core.ports.repository.AddressRepository;
import pl.paw1470.cinematac.core.model.AddressDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

    private AddressMapperImpl addressMapper = new AddressMapperImpl();

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

    @Override
    public void deleteAll() {
        String hql = "DELETE FROM Address ";
        Query query = entityManager.createQuery(hql);
        int result = query.executeUpdate();
    }
}
