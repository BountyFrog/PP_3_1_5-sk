package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String welcomeToPage() {
        return "login";
    }

    @GetMapping("/logoutPage")
    public String logoutPage() {
        return "logoutPage";
    }

    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }

    @GetMapping("/user-data")
    public ResponseEntity<User> showUserDetails(Authentication authentication) {
        UserDetailsImpl personDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<>(personDetails.getUser(), HttpStatus.OK);
    }
}