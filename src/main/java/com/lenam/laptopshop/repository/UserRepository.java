package com.lenam.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenam.laptopshop.domain.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // User save(User user);

    List<User> findByEmail(String email);

    void deleteById(long id);

    boolean existsByEmail(String email);

    // Example
    List<User> findByEmailAndAddress(String email, String address);

    User findFirstUserByEmail(String email);

    User findFirstUserById(long id);

    User findById(long id);
}
