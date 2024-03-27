package mk.ukim.finki.prva_aud_veb.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ShoppingCartNotFound extends RuntimeException{
    public ShoppingCartNotFound(Long id) {
        super(String.format("ShoppingCart %d was not found", id));
    }
}
