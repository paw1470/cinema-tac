package pl.paw1470.cinematac.core.model;

public class ReservationDAO {
    private Long id;
    private SeanceDAO seance;
    private boolean isConfirmed;
    private TicketDAO ticket;
    private String email;
    private String tel;
    private String name;
    private String surname;
    private int row;
    private int column;

    public ReservationDAO(Long id, SeanceDAO seance, boolean isConfirmed, TicketDAO ticket, String email, String tel, String name, String surname, int row, int column) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public SeanceDAO getSeance() {
        return seance;
    }

    public boolean isConfirmed() {
        return isConfirmed;
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

    public TicketDAO getTicket() {
        return ticket;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
