package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.Role;
import mk.ukim.finki.prva_aud_veb.model.exception.*;
import mk.ukim.finki.prva_aud_veb.repository.jpa.UserRepository;
import mk.ukim.finki.prva_aud_veb.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordDoNotMatch();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExist(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }
}


