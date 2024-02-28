package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.KundeRepository;
import net.ictcampus.audit.model.models.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KundeService {
    private final KundeRepository kundeRepository;

    @Autowired
    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public Iterable<Kunde> findAll() {
        return kundeRepository.findAll();
    }
}
