package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable("productId") long productId) {

        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";

        this.productService.handleAddProductToCart(email, productId);

        return ResponseEntity.ok().body("Add product to cart successful");
    }
}
