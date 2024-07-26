package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller.admin;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResDashboardDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.OrderService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.ProductService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;

@RestController
@RequestMapping("/api/v1/dashboards")
public class DashboardController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    public DashboardController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<ResDashboardDTO> getDashBoard() {

        ResDashboardDTO resDashboardDTO = new ResDashboardDTO();
        resDashboardDTO.setCountUser(userService.getCountUser());
        resDashboardDTO.setCountProduct(productService.getCountProduct());
        resDashboardDTO.setCountOrder(orderService.getCountOrder());

        return ResponseEntity.ok().body(resDashboardDTO);
    }
}
