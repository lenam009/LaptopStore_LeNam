package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.CartService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Cart> handleGetCartByUser() throws InvalidException {

        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        Optional<User> userOptional = this.userService.getUserByEmail(email);

        if (!userOptional.isPresent()) {
            throw new InvalidException("Id user invalid...");
        }

        Cart cart = this.cartService.getCartByUser(userOptional.get());

        return ResponseEntity.ok().body(cart);
    }
}
