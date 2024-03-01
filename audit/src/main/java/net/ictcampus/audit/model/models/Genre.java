package net.ictcampus.audit.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    //Die beiden Models bzw. Entitäten stehen in der Datenbank in einer Beziehung
    // Bei der Ausgabe eines Audiobuches erhalten wir das dazugehörige genre und bei der Ausgabe eines Genres erhalten wir ein Set an Audiobuecher
    //JsonBackReference kümmert sich darum, das bei der Ausgabe eines Genres nicht das Problem auftaucht,
    // dass ein Genre Audiobuecher enthält, Audiobuecher wiederum Genres und diese wieder Audiobuecher, etc. Wir unterbinden somit eine Endlosschlaufe bei der Ausgabe.
    @OneToMany(mappedBy = "genre")
    @JsonBackReference
    private Set<Audiobuch> audiobuecher = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Audiobuch> getAudiobuecher() {
        return audiobuecher;
    }

    public void setAudiobuecher(Set<Audiobuch> audiobuecher) {
        this.audiobuecher = audiobuecher;
    }
}