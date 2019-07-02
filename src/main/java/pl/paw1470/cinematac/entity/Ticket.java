package pl.paw1470.cinematac.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_type")
    private String ticketType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_seance", insertable = false, updatable = false)
    private Seance ticketSeance;

    @Column(name = "ticket_price")
    private double price;

    @Column(name = "ticket_is_active")
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public Seance getTicketSeance() {
        return ticketSeance;
    }

    public double getPrice() {
        return price;
    }

    public Ticket(String ticketType, Seance ticketSeance, double price, boolean isActive) {
        this.ticketType = ticketType;
        this.ticketSeance = ticketSeance;
        this.price = price;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
