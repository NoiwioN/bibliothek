package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.UserRepository;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

}
