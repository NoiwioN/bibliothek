package net.ictcampus.audit.controller.services;

import net.ictcampus.audit.controller.repositories.UserRepository;
import net.ictcampus.audit.model.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Beuntername wird in der Datenbank gesucht wenn er existiert wird der Beutzername mit passwort zurück gegeben
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByBenutzername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getBenutzername(), user.getPasswort(),
                emptyList());
    }
}
