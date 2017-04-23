package application.service;

import application.dao.ProductDAO;
import application.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: militer
 * Date: 02.04.2017.
 */
@Service
public class ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public synchronized void createProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProduct(int productId) {
        return productDAO.getProductById(productId);
    }

    public List<Product> getProducts(){
        return productDAO.getAllProducts();
    }
}
