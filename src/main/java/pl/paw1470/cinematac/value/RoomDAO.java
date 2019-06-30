package pl.paw1470.cinematac.value;

public class RoomDAO {
    private Long id;
    private CinemaDAO cinema;
    private String name;
    private String info;

    public RoomDAO(Long id, CinemaDAO cinema, String name, String info) {
        this.id = id;
        this.cinema = cinema;
        this.name = name;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public CinemaDAO getCinema() {
        return cinema;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
