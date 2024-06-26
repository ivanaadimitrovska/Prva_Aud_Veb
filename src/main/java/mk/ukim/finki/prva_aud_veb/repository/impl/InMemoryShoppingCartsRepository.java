package mk.ukim.finki.prva_aud_veb.repository.impl;

import mk.ukim.finki.prva_aud_veb.bootstrap.DataHolder;
import mk.ukim.finki.prva_aud_veb.model.ShoppingCart;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.ShoppingCartEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartsRepository {

    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartEnum status){
        return DataHolder.shoppingCarts.stream().filter(r->r.getUser().getUsername().equals(username)
                                                        && r.getStatus().equals(status)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
         DataHolder.shoppingCarts.removeIf(r -> r.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
         DataHolder.shoppingCarts.add(shoppingCart);
         return shoppingCart;
    }
}
