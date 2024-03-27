package mk.ukim.finki.prva_aud_veb.web.controller;


import mk.ukim.finki.prva_aud_veb.model.enumCreations.Role;
import mk.ukim.finki.prva_aud_veb.model.exception.InvalidArgumentsException;
import mk.ukim.finki.prva_aud_veb.model.exception.PasswordDoNotMatch;
import mk.ukim.finki.prva_aud_veb.service.UserService;
import mk.ukim.finki.prva_aud_veb.service.implementation.AvtentikacijaImplementation;
import mk.ukim.finki.prva_aud_veb.service.implementation.UserServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AvtentikacijaImplementation avtentikacijaImplementation;
    private final UserService userService;

    public RegisterController(AvtentikacijaImplementation avtentikacijaImplementation, UserService userService) {
        this.avtentikacijaImplementation = avtentikacijaImplementation;
        this.userService = userService;
    }

    @GetMapping
    public String vrati(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordDoNotMatch exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
