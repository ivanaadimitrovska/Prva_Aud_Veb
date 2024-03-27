package mk.ukim.finki.prva_aud_veb.repository.impl;

import mk.ukim.finki.prva_aud_veb.bootstrap.DataHolder;
import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturerList;
    }

    public Optional<Manufacturer> findById(Long id){
        return DataHolder.manufacturerList.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public Optional<Manufacturer> save(String address, String name){
        Manufacturer manufacturer=new Manufacturer(address, name);
        DataHolder.manufacturerList.removeIf(r->equals(manufacturer));
        DataHolder.manufacturerList.add(manufacturer);
        return Optional.of(manufacturer);
    }

    public boolean deleteById(Long id){
        return DataHolder.manufacturerList.removeIf(r->r.getId().equals(id));
    }
}
