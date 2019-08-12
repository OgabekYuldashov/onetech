package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.domain.Product;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/uploads";

    @ModelAttribute("categories")
    public List<Category> addCategories(){
       return categoryService.findAll();
    }

    @GetMapping("/addProduct")
    public String getProductForm(@ModelAttribute("product") Product product , Model model){
        return "productAddForm";
    }
    @PostMapping("/addProduct")
    public String addProduct(Product product){

        String result = null;
        try {
            result = this.saveUploadedFiles(product.getProductImages());
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("ppp "+product.getDiscountRate());

       product.calculateDiscount( product.getDiscountRate());
        MultipartFile[] images=product.getProductImages();
        product.setPictureUrls(result);
        product.setDateProductAdded(new Date());
        productService.save(product);
       return "productAddForm";
    }

    private String saveUploadedFiles(MultipartFile[] files) throws IOException {

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath = uploadDirectory + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/productUpdate")
    public String getProductForUpdate(@RequestParam ("id") Long id,Model model){
        model.addAttribute("product",productService.getOneProductById(id));
        System.out.println("hereeeee");
        return "productUpdateForm";
    }
    @PostMapping("/productUpdate")
    public String updateProduct(Product product){
        System.out.println("idddd" +product.getId());
        productService.save(product);
        return "";
    }


}
