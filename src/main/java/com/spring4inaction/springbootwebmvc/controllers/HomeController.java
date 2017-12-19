package com.spring4inaction.springbootwebmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private HttpServletRequest request;

    public HomeController(HttpServletRequest request){
        this.request = request;
    }

    @RequestMapping("/")
    public String home(Model model){
        boolean isAdmin = request.isUserInRole("ADMIN");
        String username = request.getUserPrincipal()!=null?request.getUserPrincipal().getName():null;
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
