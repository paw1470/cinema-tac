package pl.paw1470.cinematac.core.ports.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.core.DAO.AddressDAO;

@Repository
public interface AddressRepository {
    Address getById(Long id);
    AddressDAO getByIdDao(Long id);
    Address add(AddressDAO address);
    void delete(Long id);
}
