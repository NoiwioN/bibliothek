package net.ictcampus.audit.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Audiobuch {
    //Primary Id’s werden automatisch von der Datenbank generiert und dürfen nicht gesetzt werden.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Titel darf nicht leer sein")
    @NotNull(message = "Titel muss angegeben werden")
    private String titel;
    @NotNull(message = "Laenge muss angegeben werden")
    private Integer laenge;
    @NotBlank(message = "Autor darf nicht leer sein")
    @NotNull(message = "Autor muss angegeben werden")
    private String autor;

    //Die beiden Models bzw. Entitäten stehen in der Datenbank in einer Beziehung
    //Bei der Ausgabe eines Audiobuches erhalten wir das dazugehörige genre und bei der Ausgabe eines Genres erhalten wir ein Set an Audiobuecher
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

    public Integer getLaenge() {
        return laenge;
    }

    public void setLaenge(Integer laenge) {
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
