package pl.paw1470.cinematac.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_type", insertable = false, updatable = false)
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_seance", insertable = false, updatable = false)
    private Seance ticketSeance;

    @Column(name = "ticket_price")
    private double price;
}
