package com.lenam.laptopshop.service.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.domain.Product_;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecs {

    public static Specification<Product> nameLike(String name) {
        return (root, query, builder) -> builder.equal(root.get(Product_.NAME), name);
    }

    public static Specification<Product> priceLike(double lowPrice, double highPrice) {
        return (root, query, builder) -> builder.and(builder.greaterThanOrEqualTo(root.get(Product_.PRICE), lowPrice),
                builder.lessThanOrEqualTo(root.get(Product_.PRICE), highPrice));
    }

    public static Specification<Product> minPrice(double price) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(Product_.PRICE), price);
    }

    public static Specification<Product> columnEqual(String column, String value) {
        return (root, query, builder) -> builder.equal(root.get(column), value);
    }

    public static Specification<Product> filterFactory(List<String> values) {
        return (root, query, builder) -> builder.in(root.get(Product_.FACTORY)).value(values);
    }

    public static Specification<Product> filterTarget(List<String> values) {
        return (root, query, builder) -> builder.in(root.get(Product_.TARGET)).value(values);
    }

    public static Specification<Product> filterPrice(List<Double> price) {
        return ProductSpecs.priceLike(price.get(0), price.get(1));
    }

    public static Specification<Product> columnEqualGeneral(Map<String, String> listColumnValue,
            double lowPriceValue, double highPriceValue) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();

            for (var x : listColumnValue.entrySet()) {
                if (x.getKey() == Product_.PRICE) {
                    predicates.add(builder.and(builder.greaterThanOrEqualTo(root.get(Product_.PRICE), lowPriceValue),
                            builder.lessThanOrEqualTo(root.get(Product_.PRICE), highPriceValue)));
                } else {
                    predicates.add(builder.equal(root.get(x.getKey()), x.getValue()));
                }
            }

            Predicate finalQuery = builder.and(predicates.toArray(new Predicate[0]));
            return finalQuery;
        };
    }

    public static Specification<Product> matchMultiplePrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(Product_.PRICE), min, max);
    }

}
