package com.lenam.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.CartDetail;
import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.repository.CartDetailRepository;
import com.lenam.laptopshop.repository.CartRepository;
import com.lenam.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleDeleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void handleAddProductToCart(String email, long productId) {
        // Check - Does current user has cart?
        User user = this.userService.getOneUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findFirstCartByUser(user);

            if (cart == null) {
                // Cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                // Create new Cart
                newCart = this.cartRepository.save(newCart);

                // Cart detail
                CartDetail newCartDetail = new CartDetail();
                Optional<Product> productOptional = this.productRepository.findById(productId);
                if (productOptional.isPresent()) {
                    Product realProduct = productOptional.get();
                    newCartDetail.setCart(newCart);
                    newCartDetail.setProduct(realProduct);
                    newCartDetail.setQuantity(1);
                    newCartDetail.setPrice(realProduct.getPrice());
                    // Create new CartDetail
                    this.cartDetailRepository.save(newCartDetail);
                }

            } else {
                Optional<Product> productOptional = this.productRepository.findById(productId);

                if (productOptional.isPresent()) {
                    Product realProduct = productOptional.get();

                    // Check - Does cart of current user has this product?
                    Optional<CartDetail> cartDetail = this.cartDetailRepository
                            .findFirstCartDetailByCartAndProduct(cart, realProduct);

                    if (cartDetail.isPresent()) {
                        CartDetail realCartDetail = cartDetail.get();
                        realCartDetail.setQuantity(realCartDetail.getQuantity() + 1);
                        // Update quantity cart detail
                        this.cartDetailRepository.save(realCartDetail);
                    } else {
                        CartDetail newCartDetail = new CartDetail();
                        newCartDetail.setCart(cart);
                        newCartDetail.setProduct(realProduct);
                        newCartDetail.setQuantity(1);
                        newCartDetail.setPrice(realProduct.getPrice());
                        // Create new cart detail
                        this.cartDetailRepository.save(newCartDetail);
                    }
                    // End check...
                }

                // Product product = this.productRepository.findById(2).get();

                // Optional<CartDetail> cartDetail =
                // this.cartDetailRepository.findFirstCartDetailByCartAndProduct(cart,
                // product);

                // CartDetail realCartDetail = cartDetail.get();

                // System.out.println("cartDetail: " + cartDetail.get().getId());
            }
        }
        // End check...
    }

}
