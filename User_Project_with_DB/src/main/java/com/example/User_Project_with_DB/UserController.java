package com.example.User_Project_with_DB;


import com.example.User_Project_with_DB.User;
import com.example.User_Project_with_DB.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";  // Return to the form if validation fails
        }
        
        userRepository.save(user); // Save user in the database
        model.addAttribute("user", user);
        return "result";
    }
    
    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        model.addAttribute("message", "Registration Successful!");
        return "success";
    }
}

