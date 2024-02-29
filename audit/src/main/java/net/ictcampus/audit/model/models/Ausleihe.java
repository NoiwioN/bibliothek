package net.ictcampus.audit.model.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Ausleihe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="audiobuch_id")
    private Audiobuch audiobuch;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @NotNull(message = "Ausleihdatum muss angegeben werden")
    @NotBlank(message = "Ausleihdatum darf nicht leer sein")
    private Date ausleihdatum;


    @NotNull(message = "Rückgabedatum muss angegeben werden")
    @NotBlank(message = "Rückgabedatum darf nicht leer sein")
    private Date rueckgabedatum;

    public Ausleihe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Audiobuch getAudiobuch() {
        return audiobuch;
    }

    public void setAudiobuch(Audiobuch audiobuch) {
        this.audiobuch = audiobuch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAusleihdatum() {
        return ausleihdatum;
    }

    public void setAusleihdatum(Date ausleihdatum) {
        this.ausleihdatum = ausleihdatum;
    }

    public Date getRueckgabedatum() {
        return rueckgabedatum;
    }

    public void setRueckgabedatum(Date rueckgabedatum) {
        this.rueckgabedatum = rueckgabedatum;
    }
}
