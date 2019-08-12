package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;


    @ModelAttribute("categories")
    List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }


}
