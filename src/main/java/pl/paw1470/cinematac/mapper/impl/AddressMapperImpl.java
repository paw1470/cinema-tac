package pl.paw1470.cinematac.mapper.impl;

import pl.paw1470.cinematac.entity.Address;
import pl.paw1470.cinematac.mapper.AddressMapper;
import pl.paw1470.cinematac.DAO.AddressDAO;

public class AddressMapperImpl implements AddressMapper {
    @Override
    public Address daoToEntity(AddressDAO addressDAO) {
        Address address = new Address(addressDAO.getCountry(),
                addressDAO.getCity(),
                addressDAO.getCode(),
                addressDAO.getStreet(),
                addressDAO.getNumber());
        return address;
    }

    @Override
    public AddressDAO entityToDao(Address address) {
        AddressDAO addressDAO = new AddressDAO(address.getId(),
                address.getCountry(),
                address.getCity(),
                address.getCode(),
                address.getStreet(),
                address.getNumber()
        );
        return addressDAO;
    }

    @Override
    public Address update(Address addressEntity, AddressDAO addressDAO) {
        return null;
    }
}