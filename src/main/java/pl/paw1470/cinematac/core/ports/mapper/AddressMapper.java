package pl.paw1470.cinematac.core.ports.mapper;

import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.core.model.AddressDAO;

public interface AddressMapper {
    Address daoToEntity(AddressDAO addressDAO);
    AddressDAO entityToDao(Address address);
    Address update(Address addressEntity, AddressDAO addressDAO);
}
