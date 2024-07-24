package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.key_embeddable.OrderDetailKey;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "product_id")
    private Product product;

    private long quantity;

    private double price;

}
