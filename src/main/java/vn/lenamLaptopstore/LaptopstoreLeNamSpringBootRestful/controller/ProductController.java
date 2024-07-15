package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping("/")
    public String getAllProducts() {
        return "hello";
    }

}
