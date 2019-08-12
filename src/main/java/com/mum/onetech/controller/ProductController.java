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
import java.time.LocalDate;
import java.util.Date;


@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/uploads";

    @GetMapping("/addProduct")
    public String getProductForm(@ModelAttribute("product") Product product , Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "product";
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
       return "welcome";
    }

    private String saveUploadedFiles(MultipartFile[] files) throws IOException {

        // Make sure directory exists!
//        File uploadDir = new File(UPLOAD_DIR);
//        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
//            String uploadFilePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
            String uploadFilePath = uploadDirectory + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("\n");
        }
        return sb.toString();
    }


}
