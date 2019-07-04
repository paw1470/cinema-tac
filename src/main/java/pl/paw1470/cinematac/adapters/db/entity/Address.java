package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_country")
    private String country;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_code")
    private String code;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number;

    public Address(String country, String city, String code, String street, String number) {
        this.country = country;
        this.city = city;
        this.code = code;
        this.street = street;
        this.number = number;
    }
}
