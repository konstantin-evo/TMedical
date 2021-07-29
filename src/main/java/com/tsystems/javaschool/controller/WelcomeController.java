package com.tsystems.javaschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = {"/"})
    public String loginPage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR")
                                                                || a.getAuthority().equals("NURSE")))  {
            return "redirect:/homePage";
        }
        return "login";
    }
}
