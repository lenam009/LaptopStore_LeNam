package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResCreateUserDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("")
    @ApiMessage(value = "Create User")
    public ResponseEntity<ResCreateUserDTO> createUser(@Valid @RequestBody User postUser) {

        String passwordEncode = this.passwordEncoder.encode(postUser.getPassword());
        postUser.setPassword(passwordEncode);

        User user = this.userService.handleCreateUser(postUser);

        ResCreateUserDTO resCreateUserDTO = this.userService.handleConvertUserToResCreateUserDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(resCreateUserDTO);
    }

}
