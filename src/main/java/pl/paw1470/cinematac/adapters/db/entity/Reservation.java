package pl.paw1470.cinematac.adapters.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_seance")
    private Seance seance;

    @Column(name = "reservation_is_confirmed")
    private boolean isConfirmed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_ticket")
    private Ticket ticket;

    @Column(name = "reservation_email")
    private String email;

    @Column(name = "reservation_tel")
    private String tel;

    @Column(name = "reservation_name")
    private String name;

    @Column(name = "reservation_surname")
    private String surname;

    @Column(name = "reservation_row")
    private int row;

    @Column(name = "reservation_column")
    private int column;

    public Reservation(Seance seance, boolean isConfirmed, Ticket ticket, String email, String tel, String name, String surname, int row, int column) {
        this.seance = seance;
        this.isConfirmed = isConfirmed;
        this.ticket = ticket;
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.surname = surname;
        this.row = row;
        this.column = column;
    }


    public void confirm(){
        isConfirmed = true;
    }

}
