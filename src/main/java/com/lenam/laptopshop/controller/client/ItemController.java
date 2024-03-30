package com.lenam.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id).get();

        model.addAttribute("product", product);

        return "client/product/detail";
    }

    @PostMapping("add-product-to-cart/{id}")
    public String addProductToCart(Model model, @PathVariable long id, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");

        this.productService.handleAddProductToCart(email, productId);

        return "redirect:/";
    }

}
