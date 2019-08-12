package com.mum.onetech.controller;

import com.mum.onetech.domain.Product;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
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


@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/uploads";

    @GetMapping("/product")
    public String getProductForm(@ModelAttribute("product") Product product , Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "product";
    }
    @PostMapping("/product")
    public String addProduct(Product product, @RequestParam("productImagesFile") MultipartFile[] files){
        StringBuilder filenames=new StringBuilder();
        for(MultipartFile file:files){
            Path filenameandpath= Paths.get(uploadDirectory,file.getOriginalFilename());
            filenames.append(file.getOriginalFilename());
            try {
                Files.write(filenameandpath, file.getBytes());
            }catch (IOException e){
             e.printStackTrace();
            }
        }

        System.out.println("ppp "+product);
//        product.setDiscountRate(product.getDiscountRate());


        productService.save(product);
       return "welcome";
    }



//    @Autowired
//    CategoryService categoryService;
//    @Autowired
//    ProductService productService;
//
//    @ModelAttribute("categories")
//    List<Category> getAllCategories(){
//        return categoryService.findAll();
//    }
//
//    @GetMapping("/products")
//    public String getAllProducts(@RequestParam(name = "cat", required = false) String catId, Model model){
//        if(Util.isPositiveInteger(catId)){
//            model.addAttribute("products", productService.findAllByCategoryId(Long.valueOf(catId)));
//            model.addAttribute("count", productService.getCountByCategoryId(Long.valueOf(catId)));
//        }else {
//            model.addAttribute("products", productService.findAll());
//            model.addAttribute("count", productService.getCountAll());
//        }
//
//        Product p = new Product();
//
//        return "shop";
//    }

}
