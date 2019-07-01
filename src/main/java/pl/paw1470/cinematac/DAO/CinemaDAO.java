package pl.paw1470.cinematac.DAO;

public class CinemaDAO {
    private Long id;
    private String name;
    private AddressDAO address;
    private String email;
    private String tel;
    private String info;

    public CinemaDAO(Long id, String name, AddressDAO address, String email, String tel, String info) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AddressDAO getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getInfo() {
        return info;
    }
}
