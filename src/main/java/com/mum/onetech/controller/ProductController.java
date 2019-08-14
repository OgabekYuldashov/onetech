package com.mum.onetech.controller;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Category;
import com.mum.onetech.domain.Product;
import com.mum.onetech.domain.ProductImage;
import com.mum.onetech.jsonmodel.CartModel;
import com.mum.onetech.service.BrandService;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import com.mum.onetech.util.Util;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@SessionAttributes("cartDetails")
@ControllerAdvice
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    BuyerService buyerService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/pimgs/";


    @ModelAttribute("categories")
    public List<Category> addCategories(Model model){
        model.addAttribute("brands" ,brandService.findAll());
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
    public String getAllProducts(@RequestParam(name = "cat", required = false) String catId, @RequestParam(name = "sid", required = false) String sid, @RequestParam(name = "sort", required = false) String sortMethod, Model model){

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
    @GetMapping("/addProduct")
    public String getProductForm(@ModelAttribute("product") Product product , Model model){
        return "productAddForm";
    }
    @PostMapping("/addProduct")
    public String addProduct(Product product){

        String result = null;
        List<ProductImage> result2= new ArrayList<>();
//        try {
//            result = this.saveUploadedFiles(product.getProductImages());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        try {
            result2 = this.saveImages(product.getProductImages());
        }catch (IOException e){
            e.printStackTrace();
        }

        product.setProductImgs(result2);
        product.calculateDiscount( product.getDiscountRate());
//        product.setPictureUrls(result);
        product.setDateProductAdded(new Date());
        productService.save(product);
        return "productAddForm";
    }

    private List<ProductImage> saveImages(MultipartFile[] files) throws IOException {
        List<ProductImage> images = new ArrayList<>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String fname=Util.randomUUID()+".jpg";
            String uploadFilePath =uploadDirectory +fname;
            ProductImage productImage = new ProductImage();
            productImage.setImgName(fname);
            images.add(productImage);

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);


        }

        return images;
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
