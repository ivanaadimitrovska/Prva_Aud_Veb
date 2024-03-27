package mk.ukim.finki.prva_aud_veb.service;

import mk.ukim.finki.prva_aud_veb.model.User;

public interface Avtentikacija {

    User login(String username, String password);

}
