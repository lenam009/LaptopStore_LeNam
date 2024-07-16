package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public User postMethodName(@Valid @RequestBody User postUser) {

        User user = this.userService.handleCreateUser(postUser);

        return user;
    }

}
