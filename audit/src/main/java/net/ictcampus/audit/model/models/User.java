package net.ictcampus.audit.model.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String vorname;

    private String nachname;

    private String email;
    @NotBlank(message = "Benutzername darf nicht leer sein")
    @NotNull(message = "Benutzername muss angegeben werden")
    private String benutzername;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Passwort darf nicht leer sein")
    @NotNull(message = "Passwort muss angegeben werden")
    private String passwort;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Ausleihe> ausgelieheneBucher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Set<Ausleihe> getAusgelieheneBucher() {
        return ausgelieheneBucher;
    }

    public void setAusgelieheneBucher(Set<Ausleihe> ausgelieheneBucher) {
        this.ausgelieheneBucher = ausgelieheneBucher;
    }

}
