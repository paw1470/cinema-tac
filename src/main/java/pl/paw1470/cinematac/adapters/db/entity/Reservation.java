package pl.paw1470.cinematac.adapters.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_seans", insertable = false, updatable = false)
    private Seance seance;

    @Column(name = "reservation_is_confirmed")
    private boolean isConfirmed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_ticket", insertable = false, updatable = false)
    private Ticket ticket;

    @Column(name = "reservation_email")
    private String email;

    @Column(name = "reservation_tel")
    private String tel;

    @Column(name = "reservation_name")
    private String name;

    @Column(name = "reservation_surname")
    private String surname;

    public Reservation(Seance seance, boolean isConfirmed, Ticket ticket, String email, String tel, String name, String surname) {
        this.seance = seance;
        this.isConfirmed = isConfirmed;
        this.ticket = ticket;
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.surname = surname;
    }

    public Seance getSeance() {
        return seance;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void confirm(){
        isConfirmed = true;
    }

    public Long getId() {
        return id;
    }
}
