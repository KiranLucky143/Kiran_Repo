package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.User;
import com.example.DroolsEngine.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listUsers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("users", userRepo.findByUsernameContainingIgnoreCase(keyword));
        } else {
            model.addAttribute("users", userRepo.findAll());
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("keyword", keyword); // keep entered value in search box
        return "admin/users";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(user.isEnabled());
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        userRepo.findById(id).ifPresent(user -> {
            user.setUsername(updatedUser.getUsername()); //  Fix: allow username update
            user.setRole(updatedUser.getRole());
            user.setEnabled(updatedUser.isEnabled());

            // Only update password if entered
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            userRepo.save(user);
        });
        return "redirect:/admin/users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/users";
    }

    // Show password reset modal page (optional)
    @GetMapping("/reset-password/{id}")
    public String showResetPasswordForm(@PathVariable Long id, Model model) {
        userRepo.findById(id).ifPresent(user -> model.addAttribute("user", user));
        return "admin/reset-password"; // new Thymeleaf page for password input
    }

    // Update password
    @PostMapping("/reset-password/{id}")
    public String updatePassword(@PathVariable Long id, @RequestParam String password) {
        userRepo.findById(id).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
        });
        return "redirect:/admin/users";
    }


    @GetMapping("/toggle-status/{id}")
    public String toggleStatus(@PathVariable Long id) {
        userRepo.findById(id).ifPresent(user -> {
            user.setEnabled(!user.isEnabled());
            userRepo.save(user);
        });
        return "redirect:/admin/users";
    }
}
