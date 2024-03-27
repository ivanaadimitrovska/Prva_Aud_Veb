package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.Category;
import mk.ukim.finki.prva_aud_veb.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.CategoryRepository;
import mk.ukim.finki.prva_aud_veb.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c=new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c=new Category(name, description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteAllByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
