package pl.paw1470.cinematac.repository.impl;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.repository.AddressRepository;
import pl.paw1470.cinematac.value.AddressDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    protected Address getById(Long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public AddressDAO getByIdDao(Long id) {
        return getById(id).getAddressDao();
    }

    @Override
    public void add(AddressDAO address) {
        Address address1 = new Address(address.getCountry(),
                                        address.getCity(),
                                        address.getCode(),
                                        address.getStreet(),
                                        address.getNumber());
        entityManager.persist(address1);
    }

    //update nie ma bo mysle ze lepiej usunac i dac nowy adres

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }
}
