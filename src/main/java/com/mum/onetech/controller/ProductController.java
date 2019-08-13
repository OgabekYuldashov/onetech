package com.mum.onetech.controller;

import com.mum.onetech.domain.Category;
import com.mum.onetech.domain.Product;
import com.mum.onetech.service.BrandService;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private BrandService brandService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images";

    @ModelAttribute("categories")
    public List<Category> addCategories(Model model){
        model.addAttribute("brands" ,brandService.findAll());
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
            String uploadFilePath = uploadDirectory + "/" + file.getOriginalFilename()+ Util.randomUUID();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/productUpdate")
    public String getProductForUpdate(@RequestParam ("id") Long id, Model model, HttpSession session){
        model.addAttribute("product",productService.getOneProductById(id));
        session.setAttribute("productUp",productService.getOneProductById(id));
        return "productUpdateForm";
    }
    @PostMapping("/productUpdate")
    public String updateProduct( Product product,HttpSession session){
        System.out.println("idddd" +session.getAttribute("productUp"));
        productService.delete(product);

        product.setId(null);
        productService.save(product);
        return "welcome";
    }

    @PostMapping("/productDelete")
    public @ResponseBody Product updateDelete(@RequestBody Product product){
        System.out.println("product"+product);
          productService.delete(product);
        return product;
    }


}
