package pl.paw1470.cinematac.core.DAO;

public class ReservationDAO {
    private Long id;
    private SeanceDAO seance;
    private boolean isConfirmed;
    private String ticketType;
    private double ticketPrice;
    private String email;
    private String tel;
    private String name;
    private String surname;

    public ReservationDAO(Long id, SeanceDAO seance, boolean isConfirmed, String ticketType, double ticketPrice, String email, String tel, String name, String surname) {
        this.id = id;
        this.seance = seance;
        this.isConfirmed = isConfirmed;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.surname = surname;
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

    public String getTicketType() {
        return ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
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

}
