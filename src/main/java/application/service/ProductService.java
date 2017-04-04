package application.service;

import application.model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by militer on 02.04.2017.
 */
@Service
public class ProductService {
    private static SessionFactory factory;

    public ProductService() {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public Integer createProduct(Product product) {
        Transaction tx = null;
        Integer productId = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            productId = (Integer) session.save(product);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        }
        return productId;
    }

    public Product getProduct(int productId) {
        Transaction tx = null;
        Product product = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            product = session.get(Product.class, productId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts(){
        Transaction tx = null;
        List<Product> products = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            products = session.createQuery("FROM Product").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return products;
    }
}
