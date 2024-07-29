package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResCreateUserDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
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

    public Page<User> getUsersByPageAndFilter(Specification<User> specification,
            Pageable pageable) {

        Page<User> userPage = this.userRepository.findAll(specification, pageable);

        return userPage;
    }

    public ResultPaginationDTO convertToResultPaginationDTO(Page<User> userPage) {

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(userPage.getNumber() + 1);
        meta.setPageSize(userPage.getSize());
        meta.setPages(userPage.getTotalPages());
        meta.setTotal(userPage.getTotalElements());

        resultPaginationDTO.setResult(userPage.getContent());
        resultPaginationDTO.setMeta(meta);

        return resultPaginationDTO;
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
