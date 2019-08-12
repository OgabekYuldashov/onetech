package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.domain.Product;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @ModelAttribute("categories")
    List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/products")
    public String getAllProducts(@RequestParam(name = "cat", required = false) String catId, Model model){
        if(Util.isPositiveInteger(catId)){
            model.addAttribute("products", productService.findAllByCategoryId(Long.valueOf(catId)));
            model.addAttribute("count", productService.getCountByCategoryId(Long.valueOf(catId)));
        }else {
            model.addAttribute("products", productService.findAll());
            model.addAttribute("count", productService.getCountAll());
        }

        Product p = new Product();

        return "shop";
    }

}
