package net.ictcampus.audit.controller.services;



import net.ictcampus.audit.controller.repositories.GenreRepository;
import net.ictcampus.audit.model.models.Genres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

//    public Iterable<Genre> findByName(String name){
//        return genreRepository.findAll();
//    }
    public Genres findById(Integer id){
        Optional<Genres> genre = genreRepository.findById(id);
        return genre.orElseThrow(EntityExistsException::new);
    }
    public Iterable<Genres> findAll(){
        return genreRepository.findAll();
    }
    public void insert(Genres genre){
        genreRepository.save(genre);
    }
    public void update(Genres genre){
        genreRepository.save(genre);
    }
    public void deleteById(Integer id){
        genreRepository.deleteById(id);
    }

}
