package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.CartDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Order;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.OrderDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartDetailRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.OrderDetailRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.OrderRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.ProductRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.key_embeddable.OrderDetailKey;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.constant.StatusOrderEnum;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final FileService fileService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.fileService = fileService;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Page<Product> getProductsByPageAndFilter(Specification<Product> specification,
            Pageable pageable) {

        Page<Product> productPage = this.productRepository.findAll(specification, pageable);

        return productPage;
    }

    public Product getProductById(Long id) throws InvalidException {
        Optional<Product> productOptional = this.productRepository.findFirstProductById(id);

        if (!productOptional.isPresent()) {
            throw new InvalidException("Id product not exists...");
        }

        return productOptional.get();
    }

    public ResultPaginationDTO convertToResultPaginationDTO(Page<Product> productPage) {

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(productPage.getNumber() + 1);
        meta.setPageSize(productPage.getSize());
        meta.setPages(productPage.getTotalPages());
        meta.setTotal(productPage.getTotalElements());

        resultPaginationDTO.setResult(productPage.getContent());
        resultPaginationDTO.setMeta(meta);

        return resultPaginationDTO;
    }

    public Product handleCreateProduct(Product product) {
        return this.productRepository.save(product);
    }

    public boolean checkProductIsExistsById(long id) {
        return this.productRepository.existsById(id);
    }

    public void handleDeleteProductById(long id) throws InvalidException {

        boolean rs = this.checkProductIsExistsById(id);
        if (!rs) {
            throw new InvalidException("Id product not exists");
        }

        this.productRepository.deleteById(id);
    }

    public Product handleUpdateProduct(Product product) throws InvalidException {
        Optional<Product> productOptional = this.productRepository.findById(product.getId());

        if (!productOptional.isPresent()) {
            throw new InvalidException("Id product not exists...");
        }

        productOptional.get().setName(product.getName());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setImage(product.getImage());
        productOptional.get().setDetailDesc(product.getDetailDesc());
        productOptional.get().setShortDesc(product.getShortDesc());
        productOptional.get().setQuantity(product.getQuantity());
        productOptional.get().setSold(product.getSold());

        return this.productRepository.save(productOptional.get());
    }

    public Cart handleAddProductToCart(String email, long productId) throws InvalidException {
        // Check - Does current user has cart?
        Optional<User> userOptional = this.userService.getUserByEmail(email);
        Cart cart = null;

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Cart> cartOptional = this.cartRepository.findFirstCartByUser(user);
            cart = cartOptional.isPresent() ? cartOptional.get() : null;

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
                    this.cartRepository.save(cart);
                }

                cart.setTotalPrice(cart.getTotalPrice() + realProduct.getPrice());
                this.cartRepository.save(cart);

            }
        } else {
            throw new InvalidException("Id user invalid...");
        }

        return cart;
        // End check...
    }

    public Order handlePlaceOrder(User user, Order orderPost) throws InvalidException {

        Order order = null;

        // step 1: get cart by user
        Optional<Cart> cartOptional = this.cartRepository.findFirstCartByUser(user);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<CartDetail> cartDetails = cart.getCartDetails();

            // create order
            order = new Order();
            order.setUser(user);
            order.setReceiverName(orderPost.getReceiverName());
            order.setReceiverAddress(orderPost.getReceiverAddress());
            order.setReceiverPhone(orderPost.getReceiverPhone());
            order.setStatus(StatusOrderEnum.PENDING);
            order.setTotalPrice(cart.getTotalPrice());

            order = this.orderRepository.save(order);

            // create orderDetail
            if (cartDetails != null) {
                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    OrderDetailKey orderDetailKey = new OrderDetailKey(order.getId(), cd.getProduct().getId());

                    orderDetail.setId(orderDetailKey);
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());

                    OrderDetail newOrderDetail = this.orderDetailRepository.save(orderDetail);
                }

                // step 2: delete cart_detail and cart
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                this.cartRepository.deleteById(cart.getId());

            }
        } else {
            throw new InvalidException("User don't have cart...");
        }

        return order;

    }

    public long getCountProduct() {
        return this.productRepository.count();
    }

}
