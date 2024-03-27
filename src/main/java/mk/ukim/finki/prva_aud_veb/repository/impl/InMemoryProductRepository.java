package mk.ukim.finki.prva_aud_veb.repository.impl;

import mk.ukim.finki.prva_aud_veb.bootstrap.DataHolder;
import mk.ukim.finki.prva_aud_veb.model.Category;
import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll(){
        return DataHolder.productList;
    }

    public Optional<Product> findById(Long id){
        return DataHolder.productList.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Product> findByName(String name){
        return DataHolder.productList.stream().filter(r->r.getName().equals(name)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer){
        DataHolder.productList.removeIf(r->r.getName().equals(name));
        DataHolder.productList.add(new Product(name, price,quantity,category,manufacturer));
        return Optional.of(new Product(name, price,quantity,category,manufacturer));
    }

    public void deleteById(Long id){
        DataHolder.productList.removeIf(r->r.getId().equals(id));
    }
}
