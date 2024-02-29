package net.ictcampus.audit.controller.repositories;

import net.ictcampus.audit.model.models.Audiobuch;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AudiobuchRepository extends CrudRepository<Audiobuch, Integer> {

    public Optional<Audiobuch> findAudiobuchByTitel(String titel);
}
