package mk.ukim.finki.prva_aud_veb.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturerNotFound extends RuntimeException{
    public ManufacturerNotFound(Long id) {
        super(String.format("Manufacturer with id %d was not found", id));
    }
}
