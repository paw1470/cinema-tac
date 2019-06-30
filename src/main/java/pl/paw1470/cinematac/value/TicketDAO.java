package pl.paw1470.cinematac.value;

public class TicketDAO {
    private Long id;
    private String ticketType;
    private double price;

    public TicketDAO(Long id, String ticketType, double price) {
        this.id = id;
        this.ticketType = ticketType;
        this.price = price;
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
}
