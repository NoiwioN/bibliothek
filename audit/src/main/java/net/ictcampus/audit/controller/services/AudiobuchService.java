package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.AudiobuchRepository;
import net.ictcampus.audit.model.models.Audiobuch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AudiobuchService {
    private final AudiobuchRepository audiobuchRepository;
    @Autowired
    public AudiobuchService(AudiobuchRepository audiobuchRepository){
        this.audiobuchRepository = audiobuchRepository;
    }
    public Audiobuch findById(Integer id){
        Optional<Audiobuch> audiobuch = audiobuchRepository.findById(id);
        return audiobuch.orElseThrow(EntityExistsException::new);
    }
    public Iterable<Audiobuch> findAll(){
        Iterable<Audiobuch> audiobuecher = audiobuchRepository.findAll();
        return audiobuecher;
    }
    public void insert(Audiobuch audiobuch){
        audiobuchRepository.save(audiobuch);
    }
    public void update(Audiobuch audiobuch){
        audiobuchRepository.save(audiobuch);
    }
    public void deleteById(Integer id){
        audiobuchRepository.deleteById(id);
    }

    public Audiobuch findByName(String titel) {
        Optional<Audiobuch> audiobuch= audiobuchRepository.findAudiobuchByTitel(titel);
        return audiobuch.orElseThrow(EntityNotFoundException::new);
    }
}
