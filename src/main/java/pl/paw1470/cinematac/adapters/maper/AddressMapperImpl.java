package pl.paw1470.cinematac.adapters.maper;

import pl.paw1470.cinematac.adapters.db.entity.Address;
import pl.paw1470.cinematac.core.model.AddressDAO;
import pl.paw1470.cinematac.core.ports.mapper.AddressMapper;

public class AddressMapperImpl implements AddressMapper {
    @Override
    public Address daoToEntity(AddressDAO addressDAO) {
        Address address = new Address("",
                addressDAO.getCity(),
                "",
                "",
                ""
        );
        return address;
    }

    @Override
    public AddressDAO entityToDao(Address address) {
        AddressDAO addressDAO = new AddressDAO(address.getId(),
                "",
                address.getCity(),
                "",
                "",
                ""
        );
        return addressDAO;
    }



    @Override
    public Address update(Address addressEntity, AddressDAO addressDAO) {
        return null;
    }

    public AddressDAO fastDao(String city){
        AddressDAO addressDAO = new AddressDAO(1L,
                "",
                city,
                "",
                "",
                ""
        );
        return addressDAO;
    }

}
