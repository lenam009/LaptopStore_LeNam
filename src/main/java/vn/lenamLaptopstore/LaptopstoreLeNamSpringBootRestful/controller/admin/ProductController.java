package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import com.turkraft.springfilter.boot.Filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;
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
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @ApiMessage("Get products")
    public ResponseEntity<ResultPaginationDTO> handleGetProducts(@Filter Specification<Product> specification,
            Pageable pageable) {

        Page<Product> productPage = this.productService.getProductsByPageAndFilter(specification, pageable);

        ResultPaginationDTO resultPaginationDTO = this.productService.convertToResultPaginationDTO(productPage);

        return ResponseEntity.ok().body(resultPaginationDTO);
    }

    @GetMapping("/{productId}")
    @ApiMessage("Get product by id")
    public ResponseEntity<Product> handleGetProductById(@PathVariable("productId") long productId)
            throws InvalidException {

        Product product = this.productService.getProductById(productId);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping("")
    @ApiMessage("Create product")
    public ResponseEntity<Product> handleCreateProduct(@Valid @RequestBody Product postProduct) {

        Product newProduct = this.productService.handleCreateProduct(postProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("")
    @ApiMessage("Update product")
    public ResponseEntity<Product> handleUpdateProduct(@RequestBody Product putProduct) throws InvalidException {

        Product newProduct = this.productService.handleUpdateProduct(putProduct);

        return ResponseEntity.status(HttpStatus.OK).body(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> handleDeleteProductById(@PathVariable("id") long id) throws InvalidException {

        this.productService.handleDeleteProductById(id);

        return ResponseEntity.ok().body("Delete successful");
    }

}
