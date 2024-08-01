package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Gía phải lớn hơn 0")
    private double price;
    private String image;

    @NotNull
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;
    private String shortDesc;

    @NotNull
    @Min(value = 1, message = "Số lượng tối thiểu là 1")
    private long quantity;
    private String sold;
    private String factory;
    private String target;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    // @ManyToMany(fetch = FetchType.LAZY)
    // // Vẫn có skills nhưng bỏ thuộc tính jobs của skills
    // // @JsonIgnoreProperties(value = "jobs")
    // @JoinTable(name = "user_order", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "order_id"))
    // private List<Order> orders;

}
