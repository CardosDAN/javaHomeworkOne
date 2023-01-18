package com.javahomeworkone;

import com.javahomeworkone.category.Category;
import com.javahomeworkone.category.CategoryService;
import com.javahomeworkone.list.ListService;
import com.javahomeworkone.user.User;
import com.javahomeworkone.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository repo;

    @Autowired private ListService listService;

    @GetMapping("")
    public String showHmonePage(Model model){
        long count = repo.count();
        model.addAttribute("count", count);
        return "index";
    }

    @GetMapping("/home")
    public String showPage(Model model, String keyword){
        List<com.javahomeworkone.list.List> listList = listService.listAll();
        if (keyword != null){
            model.addAttribute("listLists", listService.findByKeyword(keyword));
        }else {
            model.addAttribute("listLists", listList);
        }

        return "home";

    }

    @GetMapping("/filesupload")
    public String index(){
        return "filesupload";
    }

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
