package com.spring4inaction.springbootwebmvc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model, Authentication authentication){
        boolean isAdmin = false;
        if (authentication!=null && authentication.getAuthorities()!=null){
            for (GrantedAuthority p : authentication.getAuthorities()){
                System.out.println("GRANTED AUTHORITY IS " + p.getAuthority());
                if (p.getAuthority().equals("ROLE_ADMIN")){
                    isAdmin = true;
                }
            }
        }
        String username = authentication!=null && authentication.getPrincipal()!=null?authentication.getPrincipal().toString():null;
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("username", username);
        return "home";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/content")
    public String content(){
        return "content";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loggedOut")
    public String loggedout(){
        return "loggedout";
    }

}
