package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.prva_aud_veb.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerImplementation implements ManufacturerService {
    private final ManufacturerRepository memoryManufacturerRepository;

    public ManufacturerImplementation(ManufacturerRepository memoryManufacturerRepository) {
        this.memoryManufacturerRepository = memoryManufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return memoryManufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return memoryManufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String address, String name) {
        return Optional.of(memoryManufacturerRepository.save(new Manufacturer(address,name)));
    }

    @Override
    public void deleteById(Long id) {
         memoryManufacturerRepository.deleteById(id);
    }
}
