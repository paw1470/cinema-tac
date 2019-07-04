package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.paw1470.cinematac.core.model.RoomDAO;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_cinema")
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
}

