package com.mum.onetech;

import com.mum.onetech.controller.ProductController;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableWebSecurity
public class OnetechApplication {

    public static void main(String[] args) {
        new File(ProductController.uploadDirectory).mkdir();
        SpringApplication.run(OnetechApplication.class, args);
    }

}
