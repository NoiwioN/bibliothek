package net.ictcampus.audit.controller.repositories;

import net.ictcampus.audit.model.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
