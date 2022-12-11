package com.javahomeworkone;

import com.javahomeworkone.user.User;
import com.javahomeworkone.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String showHmonePage(){
        return "index";
    }

    @GetMapping("/home")
    public String showPage(){ return "home";}

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "registration_success";
    }
}
