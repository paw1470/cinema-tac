package pl.paw1470.cinematac.entity;

import pl.paw1470.cinematac.value.MovieDAO;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "movie_info")
    private String info;

    public Movie(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public void update(MovieDAO movie){
        if (this.id.equals(movie.getId())) {
            this.title = movie.getTitle();
            this.info = movie.getInfo();
        }
    }
}
