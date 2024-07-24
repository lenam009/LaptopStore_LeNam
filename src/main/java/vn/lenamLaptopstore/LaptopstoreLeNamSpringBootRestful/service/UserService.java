package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResCreateUserDTO;
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

    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findFirstUserByEmail(email);
    }

    public ResCreateUserDTO handleConvertUserToResCreateUserDTO(User user) {
        ResCreateUserDTO resCreateUserDTO = new ResCreateUserDTO();
        resCreateUserDTO.setAddress(user.getAddress());
        resCreateUserDTO.setAvatar(user.getAvatar());
        resCreateUserDTO.setCreatedAt(user.getCreatedAt());
        resCreateUserDTO.setEmail(user.getEmail());
        resCreateUserDTO.setFullName(user.getFullName());
        resCreateUserDTO.setId(user.getId());
        resCreateUserDTO.setPhone(user.getPhone());
        return resCreateUserDTO;
    }

    public long getCountUser() {
        return this.userRepository.count();
    }

}
