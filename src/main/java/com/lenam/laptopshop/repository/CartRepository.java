package com.lenam.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findFirstCartByUser(User user);

}
