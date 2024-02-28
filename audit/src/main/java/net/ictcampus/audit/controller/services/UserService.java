package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.UserRepository;
import net.ictcampus.audit.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return;
            }
        }
        throw new RuntimeException("User doesn't exist");
    }

    public void signUp(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


}
