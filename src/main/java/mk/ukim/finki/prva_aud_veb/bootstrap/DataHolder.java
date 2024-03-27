package mk.ukim.finki.prva_aud_veb.bootstrap;

import lombok.Getter;
import mk.ukim.finki.prva_aud_veb.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categoryList=new ArrayList<>();
    public static List<User> users=new ArrayList<>();
    public static List<Manufacturer> manufacturerList=new ArrayList<>();
    public static List<Product> productList=new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts=new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        categoryList.add(new Category("Movies", "Movies category"));
//        categoryList.add(new Category("Books", "Books Category"));
//
//        users.add(new User("ivana.dimitrovska", "id", "ivana", "dimitrovska"));
//        users.add(new User("ana.anevska", "aa", "ana", "anevska"));
//
//        Manufacturer manufacturer1=new Manufacturer("NY NY", "Nike");
//        manufacturerList.add(manufacturer1);
//        Manufacturer manufacturer2=new Manufacturer("California13", "Adidas");
//        manufacturerList.add(manufacturer2);
//        Category category=new Category("Sport", "Sport category");
//        categoryList.add(category);
//        productList.add(new Product("Ball 1", 250.36, 7, category, manufacturer1));
//        productList.add(new Product("Ball 2", 255.36, 9, category, manufacturer2));
//        productList.add(new Product("Ball 3", 257.36, 8, category, manufacturer1));
//    }
}
