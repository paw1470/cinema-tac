package pl.paw1470.cinematac.entity;

import pl.paw1470.cinematac.value.CinemaDAO;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @Column(name = "cinema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cinema_name")
    private String name;

    @Column(name = "cinema_address")
    private Address address;

    @Column(name = "cinema_email")
    private String email;

    @Column(name = "cinema_tel")
    private String tel;

    @Column(name = "cinema_info")
    private String info;

    public Cinema(String name, Address address, String email, String tel, String info) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
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

    public void changeAddress(Address address){
        this.address = address;
    }

    public void updateCinema(CinemaDAO cinema){
        if(this.id.equals(cinema.getId())){
            this.name = cinema.getName();
            this.email = cinema.getEmail();
            this.tel = cinema.getTel();
            this.info = cinema.getInfo();
        }
    }
}
