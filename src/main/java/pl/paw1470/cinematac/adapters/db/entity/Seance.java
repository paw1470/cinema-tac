package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seance")
public class Seance {

    @Id
    @Column(name = "seance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_room")
    private Room room;


    @Temporal(TemporalType.TIME)
    @Column(name = "seance_date")
    private Date seanceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_movie")
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

}
