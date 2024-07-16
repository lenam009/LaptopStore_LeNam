package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User postUser) {
        return this.userRepository.save(postUser);
    }

}
