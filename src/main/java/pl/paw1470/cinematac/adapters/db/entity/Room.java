package pl.paw1470.cinematac.adapters.db.entity;

import pl.paw1470.cinematac.core.DAO.RoomDAO;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_cinema", insertable = false, updatable = false)
    private Cinema cinema;

    @Column(name = "room_rows")
    private int rows;

    @Column(name = "room_columns")
    private int columns;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_info")
    private String info;

    public Room(Cinema cinema, int rows, int columns, String name, String info) {
        this.cinema = cinema;
        this.rows = rows;
        this.columns = columns;
        this.name = name;
        this.info = info;
    }

    public void update(RoomDAO room){
        if(this.id.equals(room.getId())){
            this.name = room.getName();
            this.name = room.getInfo();
        }
    }

    public Long getId() {
        return id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

