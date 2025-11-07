package com.example.library_management.controller
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AuthController {
    @GetMapping("/login")
    String login() {
        return "login"
    }

    @GetMapping("/signup")
    String signup() {
        return "signup"
    }

    @PostMapping("/signup")
    String signup(@RequestParam String name,
                  @RequestParam String email,
                  @RequestParam String password,
                  @RequestParam String confirmPassword,
                  RedirectAttributes redirectAttributes) {
        if (!name || !email || !password || !confirmPassword) {
            redirectAttributes.addFlashAttribute("error", "All fields are required.")
            return "redirect:/signup"
        }
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.")
            return "redirect:/signup"
        }
        redirectAttributes.addFlashAttribute("success", "Account created successfully! Please log in.")
        return "redirect:/login"
    }
}