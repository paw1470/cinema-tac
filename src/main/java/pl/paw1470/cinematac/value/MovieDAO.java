package pl.paw1470.cinematac.value;

public class MovieDAO {
    private Long id;
    private String title;
    private String info;

    public MovieDAO(Long id, String title, String info) {
        this.id = id;
        this.title = title;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }
}
