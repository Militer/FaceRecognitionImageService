package application.dao;

import application.model.Product;

import java.util.List;

/**
 * User: militer
 * Date: 23.04.2017.
 */
public interface ProductDAO {
    void addProduct(Product product);

    Product getProductById(int productId);

    List<Product> getAllProducts();

    void updateProduct(Product newProduct);

    void deleteProduct(int productId);
}
