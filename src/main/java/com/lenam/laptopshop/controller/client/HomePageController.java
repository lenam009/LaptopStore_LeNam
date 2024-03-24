package com.lenam.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.domain.dto.RegisterDTO;
import com.lenam.laptopshop.service.ProductService;
import com.lenam.laptopshop.service.RoleService;
import com.lenam.laptopshop.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String HomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    // Register
    @GetMapping("/register")
    public String RegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(Model model, @ModelAttribute("registerUser") @Valid RegisterDTO registerUser,
            BindingResult newUserBindingResult) {
        System.out.println("registerUser: " + registerUser);

        // List<FieldError> errors = newUserBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println("newUserValidate: " + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        if (newUserBindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User user = this.userService.registerDTOtoUser(registerUser);

        String passwordEncode = this.passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncode);
        user.setRole(this.roleService.getRoleByName("user"));

        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String LoginPage(Model model) {

        return "client/auth/login";
    }

}
