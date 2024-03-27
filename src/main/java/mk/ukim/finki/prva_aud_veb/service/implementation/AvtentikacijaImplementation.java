package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidArgumentsException;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidUser;
import mk.ukim.finki.prva_aud_veb.repository.jpa.UserRepository;
import mk.ukim.finki.prva_aud_veb.service.Avtentikacija;
import org.springframework.stereotype.Service;


@Service
public class AvtentikacijaImplementation implements Avtentikacija {

    private final UserRepository inMemoryUserRepository;

    public AvtentikacijaImplementation(UserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return inMemoryUserRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUser::new);
    }

//    @Override
//    public User registration(String username, String password, String repeatPassword, String name, String surname) {
//        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
//            throw new InvalidArgumentsException();
//        }
//        if(!password.equals(repeatPassword)){
//            throw new PasswordDoNotMatch();
//        }
//
//        if(this.inMemoryUserRepository.findByUsername(username).isPresent() || !this.inMemoryUserRepository.findByUsername(username).isEmpty()){
//            throw new UsernameAlreadyExist(username);
//        }
//        User user=new User(username, password, name,surname);
//        return inMemoryUserRepository.save(user);
//    }
}
