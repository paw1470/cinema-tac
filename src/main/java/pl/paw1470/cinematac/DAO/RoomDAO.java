package pl.paw1470.cinematac.DAO;

public class RoomDAO {
    private Long id;
    private CinemaDAO cinema;
    private int rows;
    private int columns;
    private String name;
    private String info;

    public RoomDAO(Long id, CinemaDAO cinema, int rows, int columns, String name, String info) {
        this.id = id;
        this.cinema = cinema;
        this.rows = rows;
        this.columns = columns;
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
