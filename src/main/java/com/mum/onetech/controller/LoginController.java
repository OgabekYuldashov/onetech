package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("loginError.....");
        model.addAttribute("loginError", true);
        return "login";
    }
}
