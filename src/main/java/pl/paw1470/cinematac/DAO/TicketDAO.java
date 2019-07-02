package pl.paw1470.cinematac.DAO;

public class TicketDAO {
    private Long id;
    private String ticketType;
    private double price;
    private boolean isActive;
    private SeanceDAO seanceDAO;

    public TicketDAO(Long id, String ticketType, double price, boolean isActive, SeanceDAO seanceDAO) {
        this.id = id;
        this.ticketType = ticketType;
        this.price = price;
        this.isActive = isActive;
        this.seanceDAO = seanceDAO;
    }

    public Long getId() {
        return id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

    public SeanceDAO getSeanceDAO() {
        return seanceDAO;
    }
}
