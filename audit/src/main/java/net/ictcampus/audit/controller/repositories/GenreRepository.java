package net.ictcampus.audit.controller.repositories;

import net.ictcampus.audit.model.models.Genres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genres, Integer> {
}
