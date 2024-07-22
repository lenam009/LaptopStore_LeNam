package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findFirstProductById(long id);

}
