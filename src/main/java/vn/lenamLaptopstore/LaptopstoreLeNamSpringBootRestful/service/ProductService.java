package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Product;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResultPaginationDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.repository.ProductRepository;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.exception.InvalidException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getProductsByPageAndFilter(Specification<Product> specification,
            Pageable pageable) {

        Page<Product> productPage = this.productRepository.findAll(specification, pageable);

        return productPage;
    }

    public Product getProductById(Long id) throws InvalidException {
        Optional<Product> productOptional = this.productRepository.findFirstProductById(id);

        if (!productOptional.isPresent()) {
            throw new InvalidException("Id product not exists...");
        }

        return productOptional.get();
    }

    public ResultPaginationDTO convertToResultPaginationDTO(Page<Product> productPage) {

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();

        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();
        meta.setPage(productPage.getNumber() + 1);
        meta.setPageSize(productPage.getSize());
        meta.setPages(productPage.getTotalPages());
        meta.setTotal(productPage.getTotalElements());

        resultPaginationDTO.setResult(productPage.getContent());
        resultPaginationDTO.setMeta(meta);

        return resultPaginationDTO;
    }

    public Product handleCreateProduct(Product product) {
        return this.productRepository.save(product);
    }

    public boolean checkProductIsExistsById(long id) {
        return this.productRepository.existsById(id);
    }

    public void handleDeleteProductById(long id) throws InvalidException {

        boolean rs = this.checkProductIsExistsById(id);
        if (!rs) {
            throw new InvalidException("Id product not exists");
        }

        this.productRepository.deleteById(id);
    }

}
