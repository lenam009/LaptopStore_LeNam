package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}