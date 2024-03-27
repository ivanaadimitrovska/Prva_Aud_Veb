package mk.ukim.finki.prva_aud_veb.web.controller;


import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidArgumentsException;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidUser;
import mk.ukim.finki.prva_aud_veb.service.Avtentikacija;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Avtentikacija avtentikacija;

    public LoginController(Avtentikacija avtentikacija) {
        this.avtentikacija = avtentikacija;
    }

    @GetMapping
    public String getloginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user=null;
        try {
            user=this.avtentikacija.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUser exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
