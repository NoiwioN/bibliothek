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