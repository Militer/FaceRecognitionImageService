package application.controller;

import application.model.Product;
import application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by militer on 26.03.2017.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestBody Product product) {
        productService.createProduct(product);
        return "Greetings from spring boot";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public Product get(@PathVariable int productId) {
        return productService.getProduct(productId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getProducts();
    }
}
