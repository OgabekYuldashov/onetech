package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.domain.Product;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.util.Util;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/uploads";

    @ModelAttribute("categories")
    List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/product/{pid}")
    public String getProductDetails(@PathVariable("pid") String pid, Model model){
        //Redirect user to a /products if invalid id is received
        if(!Util.isPositiveInteger(pid)){
            return "redirect:/products";
        }

        Product currProduct = productService.findById(Long.valueOf(pid));

        //Redirect user to a /products if invalid id is received
//        if(currProduct == null){
//            return "redirect:/products";
//        }


        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("currProduct", currProduct);

        return "productDetails";
    }

    @GetMapping("/products")
    public String getAllProducts(@RequestParam(name = "cat", required = false) String catId, @RequestParam(name = "sort", required = false) String sortMethod, Model model){

//        Product p = new Product();
//        productService.save(p);

        List<Product> products = new ArrayList<>();
        Long product_count = 0L;

        //Retrieve products by Category Id
        if(Util.isPositiveInteger(catId)){
            products = productService.findAllByCategoryId(Long.valueOf(catId));
            product_count = productService.getCountByCategoryId(Long.valueOf(catId));

            String currCategory = "No Match Found";
            if(categoryService.finById(Long.valueOf(catId)) != null){
                model.addAttribute("currCategory", categoryService.finById(Long.valueOf(catId)).getName());
            }
        }else {
            products = productService.findAll();
            product_count = productService.getCountAll();
        }

        if(sortMethod != null){
            if(products != null && products.size() > 0){
                switch (sortMethod){
                    case "price":
                        products = products.stream()
                                .sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                        break;
                    case "name":
                        products = products.stream()
                                .sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
                        break;
                }
            }
            model.addAttribute("sortMethod", sortMethod);
        }


        model.addAttribute("products", products);
        model.addAttribute("product_count", product_count);

//        model.addAttribute("categories", categoryService.findAll());
        return "shop";
    }

}
