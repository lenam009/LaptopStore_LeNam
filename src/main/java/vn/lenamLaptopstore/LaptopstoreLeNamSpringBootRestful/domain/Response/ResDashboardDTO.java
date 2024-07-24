package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResDashboardDTO {

    private long countUser;

    private long countProduct;

    private long countOrder;
}
