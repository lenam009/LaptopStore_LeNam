package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.CartDetail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCheckoutDTO {

    private List<CartDetail> cartDetails;

    private double totalPrice;
}
