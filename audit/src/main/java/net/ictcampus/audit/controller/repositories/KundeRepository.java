package net.ictcampus.audit.controller.repositories;

import net.ictcampus.audit.model.models.Kunde;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KundeRepository extends CrudRepository<Kunde, Integer> {
}
