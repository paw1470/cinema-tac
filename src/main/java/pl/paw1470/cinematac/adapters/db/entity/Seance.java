package pl.paw1470.cinematac.adapters.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seance")
public class Seance {

    @Id
    @Column(name = "seance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_room", insertable = false, updatable = false)
    private Room room;


    @Temporal(TemporalType.TIME)
    @Column(name = "seance_date")
    private Date seanceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_movie", insertable = false, updatable = false)
    private Movie movie;

    @Column(name = "seance_is_reservation_available")
    private boolean isReservationAvailable;

    @Column(name = "seance_is_tickets_available")
    private boolean isTicketsAvailable;

    public Seance(Room room, Date seanceDate, Movie movie, boolean isReservationAvailable, boolean isTicketsAvailable) {
        this.room = room;
        this.seanceDate = seanceDate;
        this.movie = movie;
        this.isReservationAvailable = isReservationAvailable;
        this.isTicketsAvailable = isTicketsAvailable;
    }

    public Room getRoom() {
        return room;
    }

    public Date getSeanceDate() {
        return seanceDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isReservationAvailable() {
        return isReservationAvailable;
    }

    public boolean isTicketsAvailable() {
        return isTicketsAvailable;
    }

    public void closeReservation(){
        isReservationAvailable = false;
    }

    public void openReservation(){
        isReservationAvailable = true;
    }

    public void closeTicket(){
        isTicketsAvailable = false;
    }

    public void openTicket(){
        isTicketsAvailable = true;
    }

    public Long getId() {
        return id;
    }
}
