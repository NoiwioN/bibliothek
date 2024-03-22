package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.AusleiheRepository;
import net.ictcampus.audit.model.models.Ausleihe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AusleiheService {
    private AusleiheRepository ausleiheRepository;

    @Autowired
    public AusleiheService(AusleiheRepository ausleiheRepository) {
        this.ausleiheRepository = ausleiheRepository;
    }

    public Iterable<Ausleihe> findAll(){
        return ausleiheRepository.findAll();
    }


    public Ausleihe findById(Integer id) {
        Optional<Ausleihe> ausleihe = ausleiheRepository.findById(id);
        return ausleihe.orElseThrow(EntityNotFoundException::new);
    }

    public void update(Ausleihe ausleihe) {
        Iterable<Ausleihe> ausleihen= findAll();
        for(Ausleihe a:ausleihen){
            if(a.getId().equals(ausleihe.getId())){
                ausleiheRepository.save(ausleihe);
                return;
            }
        }
        throw new RuntimeException("Ausleihe gibt es nicht");
    }

    public void insert(Ausleihe ausleihe) {
        ausleiheRepository.save(ausleihe);
    }

    public void deleteById(Integer id) {
        ausleiheRepository.deleteById(id);
    }

    public Iterable<Ausleihe> findAusleiheByUserId(Integer id){
        return ausleiheRepository.findAusleiheByUserId(id);
    }
}
