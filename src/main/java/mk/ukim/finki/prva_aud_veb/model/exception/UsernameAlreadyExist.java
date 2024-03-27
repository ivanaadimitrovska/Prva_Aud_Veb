package mk.ukim.finki.prva_aud_veb.model.exception;

public class UsernameAlreadyExist extends RuntimeException{
    public UsernameAlreadyExist(String username) {

        super(String.format("Username %s already exist", username));
    }
}
