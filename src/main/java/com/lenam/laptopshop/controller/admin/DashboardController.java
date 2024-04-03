package com.lenam.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lenam.laptopshop.service.OrderService;
import com.lenam.laptopshop.service.ProductService;
import com.lenam.laptopshop.service.UserService;

@Controller
public class DashboardController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    public DashboardController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getDashBoard(Model model) {

        model.addAttribute("countUser", userService.getCountUser());
        model.addAttribute("countProduct", productService.getCountProduct());
        model.addAttribute("countOrder", orderService.getCountOrder());

        return "admin/dashboard/show";
    }
}
