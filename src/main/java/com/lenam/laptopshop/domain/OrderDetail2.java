package com.lenam.laptopshop.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Embeddable
class OrderDetailKey implements Serializable {

    @Column(name = "order_id")
    private Long orderID;

    @Column(name = "product_id")
    private Long productID;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    // standard constructors, getters, and setters
    // hashcode and equals implementation

}

@Entity
@Table(name = "order_detail2")
public class OrderDetail2 {

    @EmbeddedId
    OrderDetailKey id;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @MapsId("productID")
    @JoinColumn(name = "product_id")
    Product product;

    private long quantity;
    private double price;

    public OrderDetailKey getId() {
        return id;
    }

    public void setId(OrderDetailKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
