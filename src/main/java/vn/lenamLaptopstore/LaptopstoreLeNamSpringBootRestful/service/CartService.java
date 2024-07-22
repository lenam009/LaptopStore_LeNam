package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return this.cartRepository.findAll();
    }

    public Cart getCartByUser(User user) throws InvalidException {
        Optional<Cart> cartOptional = this.cartRepository.findFirstCartByUser(user);

        if (!cartOptional.isPresent()) {
            throw new InvalidException("User don't have cart specify...");
        }

        return cartOptional.get();
    }

}
