package pl.paw1470.cinematac.repository;

import org.springframework.stereotype.Repository;
import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.value.AddressDAO;

@Repository
public interface AddressRepository {
    AddressDAO getByIdDao(Long id);
    void add(AddressDAO address);
    void delete(Long id);
}
