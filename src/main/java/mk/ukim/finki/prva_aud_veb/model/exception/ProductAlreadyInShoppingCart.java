package mk.ukim.finki.prva_aud_veb.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCart extends RuntimeException{
    public ProductAlreadyInShoppingCart(Long productId, String username) {
        super(String.format("Proizvodot so %d id e vekje dodaden vo koshnichkata za korisnikot so %s username", productId, username));
    }
}
