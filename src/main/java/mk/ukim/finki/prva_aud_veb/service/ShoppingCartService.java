package mk.ukim.finki.prva_aud_veb.service;

import mk.ukim.finki.prva_aud_veb.model.Product;
import mk.ukim.finki.prva_aud_veb.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
