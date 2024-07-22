package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import com.turkraft.springfilter.boot.Filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<ResultPaginationDTO> handleGetProducts(@Filter Specification<Product> specification,
            Pageable pageable) {

        Page<Product> productPage = this.productService.getProductsByPageAndFilter(specification, pageable);

        ResultPaginationDTO resultPaginationDTO = this.productService.convertToResultPaginationDTO(productPage);

        return ResponseEntity.ok().body(resultPaginationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> handleGetProductById(@PathVariable("id") long id) throws InvalidException {

        Product product = this.productService.getProductById(id);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping("")
    public ResponseEntity<Product> handleCreateProduct(@Valid @RequestBody Product postProduct) {

        Product newProduct = this.productService.handleCreateProduct(postProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> handleDeleteProductById(@PathVariable("id") long id) throws InvalidException {

        this.productService.handleDeleteProductById(id);

        return ResponseEntity.ok().body("Delete successful");
    }

}
