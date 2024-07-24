package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.user;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.CartDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Order;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ProductService productService;
    private final UserService userService;

    public ItemController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<Order> handlePlaceOrder(@RequestBody Order postOrder) throws InvalidException {
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        Optional<User> userOptional = this.userService.getUserByEmail(email);

        if (!userOptional.isPresent()) {
            throw new InvalidException("Id user invalid...");
        }

        Order order = this.productService.handlePlaceOrder(userOptional.get(), postOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
