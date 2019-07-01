package pl.paw1470.cinematac.DAO;


import java.util.Date;

public class SeanceDAO {
    private Long id;
    private RoomDAO room;
    private Date seansDate;
    private MovieDAO movie;
    private boolean isReservationAvailable;
    private boolean isTicketsAvailable;

    public SeanceDAO(Long id, RoomDAO room, Date seansDate, MovieDAO movie, boolean isReservationAvailable, boolean isTicketsAvailable) {
        this.id = id;
        this.room = room;
        this.seansDate = seansDate;
        this.movie = movie;
        this.isReservationAvailable = isReservationAvailable;
        this.isTicketsAvailable = isTicketsAvailable;
    }

    public Long getId() {
        return id;
    }

    public RoomDAO getRoom() {
        return room;
    }

    public Date getSeansDate() {
        return seansDate;
    }

    public MovieDAO getMovie() {
        return movie;
    }

    public boolean isReservationAvailable() {
        return isReservationAvailable;
    }

    public boolean isTicketsAvailable() {
        return isTicketsAvailable;
    }
}
