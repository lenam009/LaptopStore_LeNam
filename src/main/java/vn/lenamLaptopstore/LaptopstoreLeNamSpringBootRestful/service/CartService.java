package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.CartDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartDetailRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
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

    public void deleteCartDetail(long cartDetailId) throws InvalidException {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);

        if (!cartDetailOptional.isPresent()) {
            throw new InvalidException("Id cart detail invalid...");
        }

        CartDetail cartDetail = cartDetailOptional.get();

        Cart cart = cartDetail.getCart();

        // Update total price cart
        cart.setTotalPrice(cart.getTotalPrice() - (cartDetail.getPrice() * cartDetail.getQuantity()));
        this.cartRepository.save(cart);

        this.cartDetailRepository.delete(cartDetail);

        int sum = cart.getSum();
        if (sum > 1) {
            cart.setSum(sum - 1);
            this.cartRepository.save(cart);
        } else {
            this.cartRepository.delete(cart);
        }
    }

    public Cart getCartOfUser(User user) throws InvalidException {

        Optional<Cart> cartOptional = this.cartRepository.findFirstCartByUser(user);

        if (!cartOptional.isPresent()) {
            throw new InvalidException("Id cart not valid...");
        }

        return cartOptional.get();

    }

}
