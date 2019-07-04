package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_type")
    private String ticketType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_seance")
    private Seance seance;

    @Column(name = "ticket_price")
    private double price;

    @Column(name = "ticket_is_active")
    private boolean isActive;

    public Ticket(String ticketType, Seance seance, double price, boolean isActive) {
        this.ticketType = ticketType;
        this.seance = seance;
        this.price = price;
        this.isActive = isActive;
    }
}
