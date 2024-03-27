package mk.ukim.finki.prva_aud_veb.repository.jpa;

import mk.ukim.finki.prva_aud_veb.model.ShoppingCart;
import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.ShoppingCartEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartEnum shoppingCartEnum);
}
