package net.ictcampus.audit.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Audiobuch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titel;
    private Date laenge;
    private String autor;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

    private Integer erscheinungsjahr;

    @OneToMany(mappedBy = "audiobuch")
    @JsonBackReference
    private List<Ausleihe> ausleihen;

    public Audiobuch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Date getLaenge() {
        return laenge;
    }

    public void setLaenge(Date laenge) {
        this.laenge = laenge;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(Integer erscheinungsjahr) {
        this.erscheinungsjahr = erscheinungsjahr;
    }
}
