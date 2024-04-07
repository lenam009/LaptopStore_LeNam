package com.lenam.laptopshop.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.CartDetail;
import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.Product_;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        this.productService.handleAddProductToCart(email, productId, session);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        Cart cart = this.productService.getCartOfUser(id);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail x : cartDetails) {
            totalPrice += x.getPrice() * x.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("total", totalPrice);

        model.addAttribute("cart", cart);

        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(Model model, @PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.productService.deleteCartDetail(id, session);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.getCartOfUser(id);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(Model model, @ModelAttribute("cart") Cart cart, HttpServletRequest request) {

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        HttpSession session = request.getSession(false);
        User currentUser = new User();// null
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        this.productService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);

        return "client/cart/thanks";
    }

    @GetMapping("/product")
    public String productPage(Model model,
            @RequestParam("page") Optional<String> pageOptional,
            @RequestParam("name") Optional<String> nameOptional) {

        int page = 1;
        String name = "";

        try {
            if (nameOptional.isPresent()) {
                name = nameOptional.get();
            } else {
                // page = 1
            }
        } catch (Exception ex) {
        }

        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception ex) {
        }

        Pageable pageable = PageRequest.of(page - 1, 3);
        // Page<Product> productsPage =
        // this.productService.getAllProductsByPageAndFilterName(pageable, name,
        // Product_.NAME);
        Page<Product> productsPage = this.productService.getAllProductsByPageAndFilterEqualColumn(pageable,
                Product_.TARGET, "GAMING");
        // Page<Product> productsPage =
        // this.productService.getAllProductsByPageAndFilterEqualColumn(pageable,
        // Product_.FACTORY,
        // "DELL");
        // Page<Product> productsPage =
        // this.productService.getAllProductsByPageAndFilterPrice(pageable,
        // 10000000,
        // 20000000);

        List<Product> productsList = productsPage.getContent();

        model.addAttribute("products", productsList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());

        return "client/homepage/product";
    }

}
