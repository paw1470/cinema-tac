package pl.paw1470.cinematac.mapper;

import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.DAO.AddressDAO;

public interface AddressMapper {
    Address daoToEntity(AddressDAO addressDAO);
    AddressDAO entityToDao(Address address);
    Address update(Address addressEntity, AddressDAO addressDAO);
}
