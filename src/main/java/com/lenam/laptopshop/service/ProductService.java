package com.lenam.laptopshop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.CartDetail;
import com.lenam.laptopshop.domain.Order;
import com.lenam.laptopshop.domain.OrderDetail;
import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.Product_;
import com.lenam.laptopshop.domain.User;
import com.lenam.laptopshop.domain.dto.ProductCriterialDTO;
import com.lenam.laptopshop.repository.CartDetailRepository;
import com.lenam.laptopshop.repository.CartRepository;
import com.lenam.laptopshop.repository.OrderDetailRepository;
import com.lenam.laptopshop.repository.OrderRepository;
import com.lenam.laptopshop.repository.ProductRepository;
import com.lenam.laptopshop.service.specification.ProductSpecs;

import jakarta.persistence.criteria.Predicate;
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

    public Page<Product> getAllProductsByPage(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    // Equal column
    public Page<Product> getAllProductsByPageAndFilterColumn(Pageable pageable,
            ProductCriterialDTO productCriterialDTO) {
        Specification<Product> combineSpec = Specification.where(null);

        if (productCriterialDTO.getFactory() == null && productCriterialDTO.getTarget() == null
                && productCriterialDTO.getPrice() == null) {
            return this.productRepository.findAll(pageable);
        }

        if (productCriterialDTO.getFactory() != null && productCriterialDTO.getFactory().isPresent()) {
            Specification<Product> currentSpecs = ProductSpecs.filterFactory(productCriterialDTO.getFactory().get());
            combineSpec = combineSpec.and(currentSpecs);
        }

        if (productCriterialDTO.getTarget() != null && productCriterialDTO.getTarget().isPresent()) {
            Specification<Product> currentSpecs = ProductSpecs.filterTarget(productCriterialDTO.getTarget().get());
            combineSpec = combineSpec.and(currentSpecs);
        }

        if (productCriterialDTO.getPrice() != null && productCriterialDTO.getPrice().isPresent()) {
            Specification<Product> currentSpecs = this
                    .fetchProductsWithSpecification(productCriterialDTO.getPrice().get());
            combineSpec = combineSpec.and(currentSpecs);
        }

        return this.productRepository.findAll(combineSpec, pageable);
    }

    // Low To Hight
    public Page<Product> getAllProductsByPageAndFilterPrice(Pageable pageable, double lowPrice, double highPrice) {
        return this.productRepository.findAll(ProductSpecs.priceLike(lowPrice, highPrice), pageable);
    }

    // Min Price
    public Page<Product> getAllProductsByPageAndFilterMinPrice(Pageable pageable, double lowPrice) {
        return this.productRepository.findAll(ProductSpecs.minPrice(lowPrice), pageable);
    }

    // Low To Hight (ex: 10-toi-15-trieu)
    public Page<Product> getAllProductsByPageAndFilterLowPriceToHighPrice(Pageable pageable, String priceString) {
        String[] priceStringArray = priceString.split("-");
        List<Double> priceArray = new ArrayList<>();
        priceArray.set(0, 0.0);
        priceArray.set(1, 1000000000.0);
        for (String x : priceStringArray) {
            if (x.matches("-?\\d+(\\.\\d+)?")) {
                if (Arrays.stream(priceStringArray).anyMatch("trieu"::equals)) {
                    priceArray.add(Double.parseDouble(x) * 1000000);
                }
            }
        }

        return this.productRepository.findAll(ProductSpecs.filterPrice(priceArray), pageable);
    }

    // Equal factory
    public Page<Product> getAllProductsByPageAndFilterFactory(Pageable pageable, List<String> factories) {
        return this.productRepository.findAll(ProductSpecs.filterFactory(factories), pageable);
    }

    // Equal target
    public Page<Product> getAllProductsByPageAndFilterTarget(Pageable pageable, List<String> targets) {
        return this.productRepository.findAll(ProductSpecs.filterTarget(targets), pageable);
    }

    public Specification<Product> fetchProductsWithSpecification(List<String> price) {
        // Init Empty Specification
        Specification<Product> combinedSpec = Specification.where(null);
        // Specification<Product> combinedSpec = (root, query, criteriaBuilder) ->
        // criteriaBuilder.disjunction();
        for (String p : price) {
            double min = 0;
            double max = 0;

            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "10-toi-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    break;
                case "15-toi-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    break;
                case "tren-20-trieu":
                    min = 20000000;
                    max = 500000000;
                    break;
                // Add more cases as needed
            }

            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        // Check if any price ranges were added (combinedSpec is empty)

        return combinedSpec;
    }

    public Page<Product> fetchProductsWithPage(Pageable page, List<String> price) {
        // Init Empty Specification
        Specification<Product> combinedSpec = Specification.where(null);
        int count = 0;
        for (String p : price) {
            double min = 0;
            double max = 0;

            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "10-toi-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    count++;
                    break;
                case "15-toi-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    count++;
                    break;
                case "tren-20-trieu":
                    min = 20000000;
                    max = 500000000;
                    count++;
                    break;
                // Add more cases as needed
            }

            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        // Check if any price ranges were added (combinedSpec is empty)
        if (count == 0) {
            return this.productRepository.findAll(page);
        }

        return this.productRepository.findAll(combinedSpec, page);
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
