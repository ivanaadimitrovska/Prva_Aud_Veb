package mk.ukim.finki.prva_aud_veb.web.controller;

import mk.ukim.finki.prva_aud_veb.model.ShoppingCart;
import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.service.implementation.ShoppingCartServiceImplementation;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shop-cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImplementation serviceImplementation;

    public ShoppingCartController(ShoppingCartServiceImplementation serviceImplementation) {
        this.serviceImplementation = serviceImplementation;
    }

    @GetMapping
    public String viewShoppingCarts(@RequestParam(required = false) String error, HttpServletRequest request, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username=request.getRemoteUser();
        ShoppingCart shoppingCart=serviceImplementation.getActiveShoppingCart(username);
        model.addAttribute("user", username);
        model.addAttribute("produkti", serviceImplementation.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request,
                                           Authentication authentication){
        try{
            User user= (User) authentication.getPrincipal();
            //String username=request.getRemoteUser();
            ShoppingCart shoppingCart=serviceImplementation.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shop-cart";
        }catch (RuntimeException exception){
            return "redirect:/shop-cart?error=" + exception.getMessage();
        }
    }
}
