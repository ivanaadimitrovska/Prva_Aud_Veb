package mk.ukim.finki.prva_aud_veb.web.controller;


import mk.ukim.finki.prva_aud_veb.model.Category;
import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.model.Product;
import mk.ukim.finki.prva_aud_veb.service.implementation.CategoryServiceImplementation;
import mk.ukim.finki.prva_aud_veb.service.implementation.ManufacturerImplementation;
import mk.ukim.finki.prva_aud_veb.service.implementation.ProductServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImplementation productServiceImplementation;
    private final CategoryServiceImplementation categoryServiceImplementation;
    private final ManufacturerImplementation manufacturerImplementation;

    public ProductController(ProductServiceImplementation productServiceImplementation, CategoryServiceImplementation categoryServiceImplementation, ManufacturerImplementation manufacturerImplementation) {
        this.productServiceImplementation = productServiceImplementation;
        this.categoryServiceImplementation = categoryServiceImplementation;
        this.manufacturerImplementation = manufacturerImplementation;
    }

    @GetMapping
    public String getProduct(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> lista=productServiceImplementation.findAll();
        model.addAttribute("listaProdukti", lista);
        model.addAttribute("bodyContent", "product");
        return "master-template";
    }

    // /product/67  ova e pathVariable
    // /product?id=67 ova e query parametar i vo funkcijata se zema so requestParam
    @DeleteMapping("/delete/{id}")      //tuka id mora vo zagradi za da kazheme deka e varijabilen del i toa e pathVariable
    public String deleteProduct(@PathVariable Long id){     //ovaa tuka shto se predava
        this.productServiceImplementation.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProduct(Model model){
        List<Category> categories=this.categoryServiceImplementation.listCategories();
        List<Manufacturer> manufacturerList=this.manufacturerImplementation.findAll();
        model.addAttribute("kategorii", categories);
        model.addAttribute("proizvoditeli", manufacturerList);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        if(productServiceImplementation.findById(id).isPresent()) {
            Product product=productServiceImplementation.findById(id).get();
            List<Category> categories = this.categoryServiceImplementation.listCategories();
            List<Manufacturer> manufacturerList = this.manufacturerImplementation.findAll();
            model.addAttribute("kategorii", categories);
            model.addAttribute("proizvoditeli", manufacturerList);
            model.addAttribute("produkt", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/product?error=ProductNotFound";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(name="id", required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category,
            @RequestParam Long manufacturer) {
        if (id != null) {
            this.productServiceImplementation.edit(id, name, price, quantity, category, manufacturer);
        } else {
            this.productServiceImplementation.save(name, price, quantity, category, manufacturer);
        }
        return "redirect:/product";
    }

}
