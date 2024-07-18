package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;

@RestController
public class TestController {

    @GetMapping("/")
    public String getHello() {

        throw new UsernameNotFoundException("Username not valid");

        // Optional<String> email = SecurityUtil.getCurrentUserLogin();

        // return "hello";
    }

}
