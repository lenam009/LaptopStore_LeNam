package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
