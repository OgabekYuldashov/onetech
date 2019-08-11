package com.mum.onetech.controller;

import com.mum.onetech.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/product")
@Controller
public class ProductController {

    @GetMapping("/")
    public String getProductForm(){

        return "product";
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
