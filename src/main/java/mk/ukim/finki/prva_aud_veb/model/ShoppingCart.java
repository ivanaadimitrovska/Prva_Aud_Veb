package mk.ukim.finki.prva_aud_veb.model;

import lombok.Data;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.ShoppingCartEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> productList;
    @Enumerated(EnumType.STRING)
    private ShoppingCartEnum status;

    public ShoppingCart(User user) {
        this.localDateTime = LocalDateTime.now();
        this.user = user;
        this.productList = new ArrayList<>();
        this.status = ShoppingCartEnum.CREATED;
    }

    public ShoppingCart() {

    }
}
