package net.ictcampus.audit.controller.repositories;

import net.ictcampus.audit.model.models.Ausleihe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AusleiheRepository extends CrudRepository<Ausleihe,Integer> {
}
