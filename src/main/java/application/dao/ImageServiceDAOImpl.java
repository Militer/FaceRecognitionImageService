package application.dao;

import application.model.ImageData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * User: militer
 * Date: 11.06.2017.
 */
@Transactional
@Repository
public class ImageServiceDAOImpl implements ImageServiceDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addImage(ImageData imageData) {
        entityManager.persist(imageData);
    }

    @Override
    public ImageData getImage(int imageId) {
        return entityManager.find(ImageData.class, imageId);
    }

    @Override
    public ImageData getImageByName(String imageName) {
        Query query = entityManager.createQuery("FROM application.model.ImageData as image WHERE image.name = :imageName");
        query.setParameter("imageName", imageName);
        return (ImageData)query.getSingleResult();
    }

    @Override
    public List<ImageData> getAllImages() {
        String hql = "FROM application.model.ImageData as image ORDER BY image.id";
        return (List<ImageData>) entityManager.createQuery(hql).getResultList();
    }
}
