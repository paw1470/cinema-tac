package pl.paw1470.cinematac.core.mapper;

import pl.paw1470.cinematac.core.entity.Address;
import pl.paw1470.cinematac.core.DAO.AddressDAO;

public interface AddressMapper {
    Address daoToEntity(AddressDAO addressDAO);
    AddressDAO entityToDao(Address address);
    Address update(Address addressEntity, AddressDAO addressDAO);
}
