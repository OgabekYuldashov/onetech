package com.mum.onetech.controller;

import com.mum.onetech.domain.Buyer;
import com.mum.onetech.domain.Role;
import com.mum.onetech.domain.RoleType;
import com.mum.onetech.domain.Seller;
import com.mum.onetech.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    BuyerService buyerService;

    @GetMapping("/register")
    public String getSellerRegistrationForm(Model model) {
        model.addAttribute("seller", new Seller());
        model.addAttribute("buyer", new Buyer());

        return "register";
    }

    @PostMapping("/register/buyer")
    public String registerBuyer(@Valid @ModelAttribute("buyer") Buyer buyer, BindingResult bindingResult, Model model){
        model.addAttribute("seller", new Seller());

        System.out.println("**************BUYER*************");
        System.out.println(buyer);

        if(bindingResult.hasErrors()){
            return "register";
        }

        buyer.getCredentials().setRole(new Role(RoleType.BUYER));
        buyer.getCredentials().setVerified(1);
        buyerService.addNew(buyer);

        return "redirect:/";
    }

    @PostMapping("/register/seller")
    public String registerSeller(@Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, Model model){
        model.addAttribute("buyer", new Buyer());

        if(bindingResult.hasErrors()){
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String welcome(){
        return "welcome";
    }




/*
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> getRoles(Model model) {
        return roleService.findAll();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute("user") User user) {

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "err.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            user.getRoles().forEach(System.out::println);
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "registration";

        }

    }*/
}
