package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema")
public class Cinema {

    @Id
    @Column(name = "cinema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cinema_name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_address")
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
}
