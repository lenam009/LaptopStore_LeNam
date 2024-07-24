package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.OrderDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.key_embeddable.OrderDetailKey;

public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, OrderDetailKey>, JpaSpecificationExecutor<OrderDetail> {

}
