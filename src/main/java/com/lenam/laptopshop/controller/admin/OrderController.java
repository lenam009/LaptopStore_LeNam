package com.lenam.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lenam.laptopshop.domain.Order;
import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getOrderPage(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);

        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("orderDetails", order.getOrderDetails());

        return "admin/order/show-orderDetail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String updateOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order", order);

        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updatePostOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("orderDetails", order.getOrderDetails());

        return "admin/order/show-orderDetail";
    }

}
