package pl.paw1470.cinematac.DAO;

public class AddressDAO {
    private Long id;
    private String country;
    private String city;
    private String code;
    private String street;
    private String number;

    public AddressDAO(Long id, String country, String city, String code, String street, String number) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.code = code;
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }
}
