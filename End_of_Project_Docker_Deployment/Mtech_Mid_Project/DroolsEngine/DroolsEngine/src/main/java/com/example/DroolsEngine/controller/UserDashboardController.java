package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.CreditCardApplication;
import com.example.DroolsEngine.model.Role;
import com.example.DroolsEngine.model.User;
import com.example.DroolsEngine.repository.CreditCardApplicationRepository;
import com.example.DroolsEngine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserDashboardController {

    @Autowired
    private CreditCardApplicationRepository cardRepo;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        // Get logged-in user
        User currentUser = null;
        if (principal != null) {
            currentUser = userRepository.findByUsername(principal.getName()).orElse(null);
        }

        if (currentUser == null) {
            // If somehow user is not found, redirect to login or error
            return "redirect:/error";
        }

        // Check admin role
        if (currentUser.getRole() != Role.ROLE_ADMIN) {
            // Redirect non-admins to error page
            return "redirect:/access-denied";
        }

        // Admin can continue: fetch cards
        List<CreditCardApplication> cards = cardRepo.findAll();
        model.addAttribute("cards", cards);
        if (!cards.isEmpty()) {
            model.addAttribute("cardAppId", cards.get(0).getAppId());
        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("username", currentUser.getUsername());

        return "user-dashboard"; // admin dashboard
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // maps to access-denied.html
    }


    @GetMapping("/help")
    public String HelpScreen() {
        return "help-info"; // maps to access-denied.html
    }

    @GetMapping("/settings")
    public String SettingsPage() {
        return "settings-page"; // maps to access-denied.html
    }


}
