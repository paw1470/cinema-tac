package pl.paw1470.cinematac.core.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SeanceDAO {
    private Long id;
    private RoomDAO room;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date seanceDate;
    private MovieDAO movie;
    private boolean isReservationAvailable;
    private boolean isTicketsAvailable;

    public SeanceDAO(Long id, RoomDAO room, Date seanceDate, MovieDAO movie, boolean isReservationAvailable, boolean isTicketsAvailable) {
        this.id = id;
        this.room = room;
        this.seanceDate = seanceDate;
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

    public Date getSeanceDate() {
        return seanceDate;
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
