package com.lenam.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lenam.laptopshop.domain.Product;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String getProductPage() {
        return "admin/product/show";
    }

    // create
    @GetMapping("/admin/product/create")
    public String createProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String postCreateUserPage(Model model, @ModelAttribute("newProduct") Product newProduct,
            @RequestParam("lenamFile") MultipartFile avatarFile) {

        System.out.println("New product: " + newProduct);
        return "admin/product/show";
    }

}
