package mk.ukim.finki.prva_aud_veb.service.implementation;

import mk.ukim.finki.prva_aud_veb.model.Product;
import mk.ukim.finki.prva_aud_veb.model.ShoppingCart;
import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.ShoppingCartEnum;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidUser;
import mk.ukim.finki.prva_aud_veb.model.exception.ProductAlreadyInShoppingCart;
import mk.ukim.finki.prva_aud_veb.model.exception.ProductNotFound;
import mk.ukim.finki.prva_aud_veb.model.exception.ShoppingCartNotFound;
import mk.ukim.finki.prva_aud_veb.repository.jpa.ProductRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.prva_aud_veb.repository.jpa.UserRepository;
import mk.ukim.finki.prva_aud_veb.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartsRepository;
    private final UserRepository userRepository;
    private final ProductRepository productService;
    public ShoppingCartServiceImplementation(ShoppingCartRepository shoppingCartsRepository, UserRepository userRepository, ProductRepository productService) {
        this.shoppingCartsRepository = shoppingCartsRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartsRepository.findById(cartId).isPresent()){
            throw new ShoppingCartNotFound(cartId);
        }
        return shoppingCartsRepository.findById(cartId).get().getProductList();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUser());

        return this.shoppingCartsRepository
                .findByUserAndStatus(user, ShoppingCartEnum.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartsRepository.save(cart);
                });


    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
        Product product=productService.findById(productId).orElseThrow(() -> new ProductNotFound(productId));
        if(shoppingCart.getProductList().stream().filter(r -> r.getId().equals(productId)).toList().size() > 0){
            throw new ProductAlreadyInShoppingCart(productId, username);
        }
        shoppingCart.getProductList().add(product);
        return shoppingCartsRepository.save(shoppingCart);
    }
}
