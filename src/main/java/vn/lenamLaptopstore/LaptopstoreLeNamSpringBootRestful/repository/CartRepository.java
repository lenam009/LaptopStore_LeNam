package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

}
