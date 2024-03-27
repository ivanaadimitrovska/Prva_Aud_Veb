package mk.ukim.finki.prva_aud_veb.service;

import mk.ukim.finki.prva_aud_veb.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long Id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer);

    void deleteById(Long id);
}
