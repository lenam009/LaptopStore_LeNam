package com.lenam.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenam.laptopshop.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
