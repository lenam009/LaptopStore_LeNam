package com.lenam.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lenam.laptopshop.domain.Product;
import com.lenam.laptopshop.service.ProductService;
import com.lenam.laptopshop.service.UploadService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;

        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception ex) {
        }

        Pageable pageable = PageRequest.of(page - 1, 2);

        Page<Product> productsPage = this.productService.getAllProductsByPage(pageable);
        List<Product> productsList = productsPage.getContent();

        model.addAttribute("products", productsList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());

        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id).get();
        model.addAttribute("product", product);

        return "admin/product/product-detail";
    }

    // create
    @GetMapping("/admin/product/create")
    public String createProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String postCreateUserPage(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult, @RequestParam("lenamFile") MultipartFile avatarFile) {

        System.out.println("New product: " + newProduct);

        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }

        String fileNameImage = this.uploadService.handleSaveUploadFile(avatarFile, "product");
        newProduct.setImage(fileNameImage);

        this.productService.handleSaveProduct(newProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("product", new Product());
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProductPage(Model model, @ModelAttribute("newProduct") Product newProduct) {

        this.productService.handleDeleteProduct(newProduct);

        return "redirect:/admin/product";
    }

    // Update
    @GetMapping("/admin/product/update/{id}")
    public String updateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id).get();
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProductPage(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult, @RequestParam("lenamFile") MultipartFile avatarFile) {

        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }

        Product currentProduct = this.productService.getProductById(newProduct.getId()).get();
        if (currentProduct != null) {
            currentProduct.setName(newProduct.getName());
            currentProduct.setPrice(newProduct.getPrice());

            String fileImageName = this.uploadService.handleSaveUploadFile(avatarFile, "product");
            if (!fileImageName.isEmpty()) {
                currentProduct.setImage(fileImageName);
                this.uploadService.deleteFile(newProduct.getImage(), "product");
            }

            currentProduct.setDetailDesc(newProduct.getDetailDesc());
            currentProduct.setShortDesc(newProduct.getShortDesc());
            currentProduct.setQuantity(newProduct.getQuantity());
            currentProduct.setFactory(newProduct.getFactory());
            currentProduct.setTarget(newProduct.getTarget());
        }

        System.out.println("New product: " + newProduct);

        this.productService.handleSaveProduct(currentProduct);
        return "redirect:/admin/product";
    }

}
