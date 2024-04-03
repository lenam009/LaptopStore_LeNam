package com.lenam.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.CartDetail;
import com.lenam.laptopshop.domain.Order;
import com.lenam.laptopshop.domain.OrderDetail;
import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.repository.CartDetailRepository;
import com.lenam.laptopshop.repository.CartRepository;
import com.lenam.laptopshop.repository.OrderDetailRepository;
import com.lenam.laptopshop.repository.OrderRepository;
import com.lenam.laptopshop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
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

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        // Check - Does current user has cart?
        User user = this.userService.getOneUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findFirstCartByUser(user);

            if (cart == null) {
                // Cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);
                // Create new Cart
                cart = this.cartRepository.save(newCart);
            }

            // Cart detail
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

                    // Increase sum of Cart by 1
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    session.setAttribute("sum", sum);
                    this.cartRepository.save(cart);
                }

            }
        }
        // End check...
    }

    public Cart getCartOfUser(long id) {
        User user = new User();
        user.setId(id);

        Cart cart = this.cartRepository.findFirstCartByUser(user);

        return cart;

    }

    public void deleteCartDetail(long cartDetailId, HttpSession session) {
        CartDetail cartDetail = this.cartDetailRepository.findById(cartDetailId).get();
        Cart cart = cartDetail.getCart();

        this.cartDetailRepository.delete(cartDetail);

        int sum = cart.getSum();
        if (sum > 1) {
            cart.setSum(sum - 1);
            session.setAttribute("sum", sum - 1);
            this.cartRepository.save(cart);
        } else {
            session.setAttribute("sum", 0);
            this.cartRepository.delete(cart);
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(
            User user, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) {

        // create order

        // create orderDetail

        // step 1: get cart by user
        Cart cart = this.cartRepository.findFirstCartByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            Order order = new Order();
            order.setUser(user);
            order.setReceiverName(receiverName);
            order.setReceiverAddress(receiverAddress);
            order.setReceiverPhone(receiverPhone);
            order.setStatus("PENDING");
            double totalPrice = 0;
            for (CartDetail cd : cartDetails) {
                totalPrice += cd.getPrice();
            }
            order.setTotalPrice(totalPrice);
            order = this.orderRepository.save(order);

            if (cartDetails != null) {
                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());

                    this.orderDetailRepository.save(orderDetail);
                }

                // step 2: delete cart_detail and cart
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                this.cartRepository.deleteById(cart.getId());

                // step 3 : update session
                session.setAttribute("sum", 0);
            }
        }

    }

    public long getCountProduct() {
        return this.productRepository.count();
    }
}
