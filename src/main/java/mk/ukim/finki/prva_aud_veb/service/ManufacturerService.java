package mk.ukim.finki.prva_aud_veb.service;

import mk.ukim.finki.prva_aud_veb.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> save(String address, String name);
    void deleteById(Long id);
}
