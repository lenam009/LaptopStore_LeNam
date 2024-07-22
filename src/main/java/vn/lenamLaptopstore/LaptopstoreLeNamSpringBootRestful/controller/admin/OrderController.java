package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Order;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.OrderDetail;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<ResultPaginationDTO> getOrderPage(@Filter Specification<Order> specification,
            Pageable pageable) {

        Page<Order> orderPage = this.orderService.getOrdersByPageAndFilter(specification, pageable);

        ResultPaginationDTO resultPaginationDTO = this.orderService.convertToResultPaginationDTO(orderPage);

        return ResponseEntity.ok().body(resultPaginationDTO);
    }
}
