package pl.paw1470.cinematac.adapters.db.entity;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_address", insertable = false, updatable = false)
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

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void changeAddress(Address address){
        this.address = address;
    }
}
