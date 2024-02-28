package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.UserRepository;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void update(User user) {
        Iterable<User> users = findAll();
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                user.setPasswort(bCryptPasswordEncoder.encode(user.getPasswort()));
                userRepository.save(user);
                return;
            }
        }
        throw new RuntimeException("User doesn't exist");
    }

    public void signUp(User user) {
        user.setPasswort(bCryptPasswordEncoder.encode(user.getPasswort()));
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}
