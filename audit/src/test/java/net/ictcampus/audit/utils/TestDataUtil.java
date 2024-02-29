package net.ictcampus.audit.utils;

import net.ictcampus.audit.model.models.Audiobuch;
import net.ictcampus.audit.model.models.Genre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestDataUtil {
    public static List<Audiobuch> getTestAudiobuecher(){
        List<Audiobuch> audiobuecher= new ArrayList<>();
        HashSet<Genre> genres = new HashSet<>();
        Genre genre=new Genre();
        genre.setId(1);
        genre.setName("Fantasy");
        genres.add(genre);

        for(int i=1;i<=3;i++){
            Audiobuch audiobuch= new Audiobuch();
            audiobuch.setId(i);
            audiobuch.setTitel("Audiobuch"+i);
            audiobuch.setLaenge(50000);
            genre.getAudiobuecher().add(audiobuch);
            audiobuch.setGenre(genre);
            audiobuecher.add(audiobuch);
        }
        return audiobuecher;
    }
}

erstelle mir