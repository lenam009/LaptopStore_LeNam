package com.lenam.laptopshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenam.laptopshop.domain.Cart;
import com.lenam.laptopshop.domain.CartDetail;
import com.lenam.laptopshop.domain.Product;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findFirstCartDetailByCartAndProduct(Cart cart, Product product);

    boolean existsByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findByCart(Cart cart);

}
