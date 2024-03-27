package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.Category;
import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.model.Product;
import mk.ukim.finki.prva_aud_veb.model.exception.CategoryNotFound;
import mk.ukim.finki.prva_aud_veb.model.exception.ManufacturerNotFound;
import mk.ukim.finki.prva_aud_veb.model.exception.ProductNotFound;
import mk.ukim.finki.prva_aud_veb.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.prva_aud_veb.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.prva_aud_veb.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.CategoryRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.ProductRepository;
import mk.ukim.finki.prva_aud_veb.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository memoryProductRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository memoryManufacturerRepository;

    public ProductServiceImplementation(ProductRepository memoryProductRepository, CategoryRepository categoryRepository, ManufacturerRepository memoryManufacturerRepository) {
        this.memoryProductRepository = memoryProductRepository;
        this.categoryRepository = categoryRepository;
        this.memoryManufacturerRepository = memoryManufacturerRepository;
    }


    @Override
    public List<Product> findAll() {
        return memoryProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long Id) {
        return memoryProductRepository.findById(Id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return memoryProductRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        Manufacturer manufacturer = this.memoryManufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));

        this.memoryProductRepository.deleteByName(name);
        return Optional.of(this.memoryProductRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }


    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Product product = this.memoryProductRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.memoryManufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));
        product.setManufacturer(manufacturer);

        this.memoryProductRepository.delete(product);
        //this.memoryProductRepository.deleteByName(name);
        return Optional.of(this.memoryProductRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }


    @Override
    public void deleteById(Long id) {
        this.memoryProductRepository.deleteById(id);
    }
}
