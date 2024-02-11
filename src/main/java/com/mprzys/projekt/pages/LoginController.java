package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.AppUser;
import com.mprzys.projekt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam Optional<String> error, Model model) {
        logger.info("GET request to /login");
        if (error.isPresent()) {
            model.addAttribute("loginError", "Invalid username or password.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        logger.info("GET request to /register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(Model model, AppUser appUserForm) {
        logger.info("POST request to /register for user: {}", appUserForm.getUsername());
        if (userRepository.findByUsername(appUserForm.getUsername()).isPresent()) {
            model.addAttribute("registrationError", "Username already exists");
            return "register";
        }
        try {
            AppUser appUser = new AppUser();
            appUser.setUsername(appUserForm.getUsername());
            appUser.setPassword(passwordEncoder.encode(appUserForm.getPassword()));
            userRepository.save(appUser);
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("Error during registration for user: {}", appUserForm.getUsername(), e);
            model.addAttribute("registrationError", "An error occurred during the registration process. Please try again.");
            return "register";
        }
    }

}
