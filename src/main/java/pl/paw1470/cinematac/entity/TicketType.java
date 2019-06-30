package pl.paw1470.cinematac.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket_type")
public class TicketType {

    @Id
    @Column(name = "ticket_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_type_name")
    private String name;

    @Column(name = "ticket_type_info")
    private String info;

    public TicketType(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}

