package application.controller;

import application.model.Product;
import application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: militer
 * Date: 26.03.2017.
 */
@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String create(@RequestBody Product product) {
        productService.createProduct(product);
        return "Greetings from spring boot";
    }

    @GetMapping("/{productId}")
    public Product get(@PathVariable("productId") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getProducts();
    }
}
