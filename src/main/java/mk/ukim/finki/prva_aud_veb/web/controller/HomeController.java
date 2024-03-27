package mk.ukim.finki.prva_aud_veb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = {"/", "/home"})     //ovde promena
public class HomeController {

    @GetMapping     //tuka promena
    public String getHomePage(Model model){
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }
    @GetMapping("/access_denied")       //tuka promena
    public String access_denied(Model model){
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }
    @PostMapping
    public String vrati(HttpServletRequest request, HttpServletResponse response, Model model){
        String product=request.getParameter("product");
        request.getSession().setAttribute("product", product);
        return "redirect:/product";
    }
}
