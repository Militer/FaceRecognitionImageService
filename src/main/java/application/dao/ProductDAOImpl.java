package application.dao;

import application.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * User: militer
 * Date: 23.04.2017.
 */
@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getProductById(int productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public List<Product> getAllProducts() {
        String hql = "FROM application.model.Product as prod ORDER BY prod.id";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void updateProduct(Product product) {
        Product newProduct = getProductById(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        entityManager.flush();
    }

    @Override
    public void deleteProduct(int productId) {
        entityManager.remove(getProductById(productId));
    }
}
