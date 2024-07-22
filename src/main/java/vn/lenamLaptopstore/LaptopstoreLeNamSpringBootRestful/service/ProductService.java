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
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartDetailRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.CartRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.OrderDetailRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.OrderRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.ProductRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

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

    public void handleAddProductToCart(String email, long productId) {
        // Check - Does current user has cart?
        Optional<User> userOptional = this.userService.getUserByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Cart> cartOptional = this.cartRepository.findFirstCartByUser(user);
            Cart cart = cartOptional.isPresent() ? cartOptional.get() : null;

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

            }
        }
        // End check...
    }

}
