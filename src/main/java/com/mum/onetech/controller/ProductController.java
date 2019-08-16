package com.mum.onetech.controller;

import com.mum.onetech.domain.*;
import com.mum.onetech.jsonmodel.CartModel;
import com.mum.onetech.jsonmodel.ProductModel;
import com.mum.onetech.service.*;
import com.mum.onetech.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
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
    @Autowired
    SellerService sellerService;

    public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/images/pimgs/";


    @ModelAttribute("categories")
    public List<Category> addCategories(){
        return categoryService.findAll();
    }

    @ModelAttribute("brands")
    public List<Brand> addBrands(){
        return brandService.findAll();
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
    public String getAllProducts(@RequestParam(name = "cat", required = false) String catId,
                                 @RequestParam(name = "bid", required = false) String bid,
                                 @RequestParam(name = "sid", required = false) String sid,
                                 @RequestParam(name = "sort", required = false) String sortMethod, Model model){

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

        //Retrieve products by Seller Id
        /*if(Util.isPositiveInteger(sid)){
            products = productService.findAllBySellerId(Long.valueOf(catId));
            product_count = productService.getCountBySellerId(Long.valueOf(catId));

            String currCategory = "No Match Found";
            if(categoryService.finById(Long.valueOf(catId)) != null){
                model.addAttribute("currCategory", categoryService.finById(Long.valueOf(catId)).getName());
            }
        }*/

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
    public String getProductForm(@ModelAttribute("product") Product product , Model model, Authentication authentication){
        if(authentication == null){
            return "redirect:/login";
        }

        Seller seller=sellerService.findOneByEmail(authentication.getName());
        if(seller == null){
            return "redirect:/login";
        }

        model.addAttribute("seller", seller);
        return "productAddForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "productAddForm";
        }

        if(authentication != null){
            Seller seller=sellerService.findOneByEmail(authentication.getName());
            if(seller.getFollowers()!=null){
                List<Buyer> buyers=seller.getFollowers();
                Util.addNotificationforFollower(buyers,"New Product");
            }
            product.setSeller(seller);
        }


        List<ProductImage> result2= new ArrayList<>();

        try {
            result2 = this.saveImages(product.getProductImages());
        }catch (IOException e){
            e.printStackTrace();
        }

        product.setProductImgs(result2);
        product.calculateDiscount( product.getDiscountRate());

        product.setDateProductAdded(new Date());

        productService.save(product);
        return "redirect:/seller";
    }

    private List<ProductImage> saveImages(MultipartFile[] files) throws IOException {
        List<ProductImage> images = new ArrayList<>();


        for (MultipartFile file : files) {

            String fileName = Util.randomUUID()+".jpg";
//            fileName = "56b7aae6-0f52-4a66-b249-86cc147cf43a.jpg";

            if (file.isEmpty()) {
                continue;
            }
            ProductImage productImage = new ProductImage();
            productImage.setImgName(fileName);
            images.add(productImage);

            byte[] bytes = file.getBytes();

            String fileLocation1 = new File("static\\images\\pimgs").getAbsolutePath() + "\\" + fileName;
            String fileLocation2 = new File("src\\main\\resources\\static\\images\\pimgs").getAbsolutePath() + "\\" + fileName;
            FileOutputStream fos = new FileOutputStream(fileLocation2);
            fos.write(bytes);
            fos.close();

        }

        return images;
    }

    /*
    private List<ProductImage> saveImages(MultipartFile[] files) throws IOException {
        List<ProductImage> images = new ArrayList<>();


        byte[] imageByteArray ....
        String fileName = "image.png";
        String fileLocation = new File("static\\images").getAbsolutePath() + "\\" + fileName;
        FileOutputStream fos = new FileOutputStream(fileLocation);
        fos.write(imageByteArray);
        fos.close();

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
*/
    @GetMapping("/productUpdate")
    public String getProductForUpdate(@RequestParam ("id") Long id, Model model, Authentication authentication){
        model.addAttribute("product",productService.getOneProductById(id));
        if(authentication == null){
            return "redirect:/login";
        }

        Seller seller=sellerService.findOneByEmail(authentication.getName());
        if(seller == null){
            return "redirect:/login";
        }

        model.addAttribute("seller", seller);

        return "productUpdateForm";
    }

    @PostMapping("/productUpdate")
    public String updateProduct( @Valid Product product,BindingResult bindingResult,Authentication authentication){
        if(bindingResult.hasErrors()){
            return "productUpdateForm";
        }
        System.out.println("**************************************");
        System.out.println(product);
        if(authentication != null){
            System.out.println("****************"+authentication.getName());
            Seller seller=sellerService.findOneByEmail(authentication.getName());
            System.out.println("****************"+seller );
            product.setSeller(seller);

        }
        product.calculateDiscount( product.getDiscountRate());
        product.setDateProductAdded(new Date());
        List<ProductImage> result= new ArrayList<>();

        try {
            result = this.saveImages(product.getProductImages());
        }catch (IOException e){
            e.printStackTrace();
        }

        product.setProductImgs(result);
        productService.save(product);
        return "redirect:/seller";
    }

    @PostMapping("/productDelete")
    public @ResponseBody  ProductModel updateDelete(@RequestBody Product product){
        System.out.println("********************"+product);
        Long id=product.getId();

        ProductModel product1= new ProductModel();
        product1.setId(id);
        product1.setType(null);
        try {
            productService.delete(product);
        }catch (Exception e){
            product1.setType("ordered");
        }


        return product1;
    }

}
