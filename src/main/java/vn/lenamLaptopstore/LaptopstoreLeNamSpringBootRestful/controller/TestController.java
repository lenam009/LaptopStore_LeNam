package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;

@RestController
public class TestController {

    @GetMapping("/")
    public String getHello() {

        Optional<String> email = SecurityUtil.getCurrentUserLogin();

        return email.get();
    }

}
