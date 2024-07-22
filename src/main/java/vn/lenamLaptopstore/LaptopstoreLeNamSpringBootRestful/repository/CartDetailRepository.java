package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long>, JpaSpecificationExecutor<CartDetail> {

}
