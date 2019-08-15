package com.mum.onetech.controller;

import com.mum.onetech.domain.Brand;
import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Category;
import com.mum.onetech.jsonmodel.CartModel;
import com.mum.onetech.service.BrandService;
import com.mum.onetech.service.BuyerService;
import com.mum.onetech.service.CategoryService;
import com.mum.onetech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("cartDetails")
@ControllerAdvice
public class ShopController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    BuyerService buyerService;

    @ModelAttribute("categories")
    List<Category> getAllCategories(){
        return categoryService.findAll();
    }
    @ModelAttribute("brands")
    public List<Brand> getAllBrands(){
        return brandService.findAll();
    }

    @ModelAttribute("cartDetails")
    CartModel getUserCartDetails(Authentication authentication){
        CartModel cartModel = new CartModel(0, 0.0);

        if(authentication == null) return cartModel;

        Buyer buyer = buyerService.findByEmail(authentication.getName());
        if(buyer == null) return cartModel;

        return new CartModel(buyer.getShoppingCart().getCartItems().size(), buyer.getShoppingCart().getTotalAmount());
    }


    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request, Authentication authentication) {

        if(authentication != null){
            if(buyerService.findByEmail(authentication.getName()) != null){
                model.addAttribute("currentBuyer", authentication.getName());
            }
        }

        return "index";
    }

    @GetMapping("/contact")
    public String getContactsPage(){

        return "contact";
    }

    @GetMapping("/cart")
    public String getCartPage(){

        return "cart";
    }

    @RequestMapping("/dummy")
    public String dummy(Model model) {
        model.addAttribute("buyer", new Buyer());
        return "fragments/frg_header";
    }


}
