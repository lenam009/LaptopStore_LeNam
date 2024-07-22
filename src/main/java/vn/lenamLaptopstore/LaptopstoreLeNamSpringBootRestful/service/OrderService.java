package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.turkraft.springfilter.boot.Filter;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Order;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Page<Order> getOrdersByPageAndFilter(Specification<Order> specification,
            Pageable pageable) {

        Page<Order> orderPage = this.orderRepository.findAll(specification, pageable);

        return orderPage;
    }

    public ResultPaginationDTO convertToResultPaginationDTO(Page<Order> orderPage) {

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(orderPage.getNumber() + 1);
        meta.setPageSize(orderPage.getSize());
        meta.setPages(orderPage.getTotalPages());
        meta.setTotal(orderPage.getTotalElements());

        resultPaginationDTO.setResult(orderPage.getContent());
        resultPaginationDTO.setMeta(meta);

        return resultPaginationDTO;
    }

}
