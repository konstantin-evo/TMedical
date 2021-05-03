package com.tsystems.javaschool.controller;

import com.tsystems.javaschool.domain.exception.NoSuchRoleException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = "/homePage")
    public String homePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {
            return "redirect:/patient/index";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("NURSE"))) {
            return "redirect:/tratment/info";
        } else {
            throw new NoSuchRoleException("Invalid user role");
        }
    }

//    @GetMapping(value = "/profile")
//    public String profile() {
//        return "auth/profile";
//    }
}