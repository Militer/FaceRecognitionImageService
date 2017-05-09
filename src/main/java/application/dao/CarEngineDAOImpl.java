package application.dao;

import application.model.CarEngine;
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
public class CarEngineDAOImpl implements CarEngineDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addCarEngine(CarEngine carEngine) {
        entityManager.persist(carEngine);
    }

    @Override
    public CarEngine getCarEngine(int carEngineId) {
        return entityManager.find(CarEngine.class, carEngineId);
    }

    @Override
    public List<CarEngine> getAllCarEngines() {
        String hql = "FROM application.model.CarEngine as prod ORDER BY prod.id";
        return (List<CarEngine>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void updateCarEngine(CarEngine carEngine) {
        CarEngine newCarEngine = getCarEngine(carEngine.getId());
        newCarEngine.setName(carEngine.getName());
        newCarEngine.setPrice(carEngine.getPrice());
        newCarEngine.setPower(carEngine.getPower());
        newCarEngine.setAcceleration(carEngine.getAcceleration());
        newCarEngine.setImagePath(carEngine.getImagePath());
        newCarEngine.setMaximumSpeed(carEngine.getMaximumSpeed());
        entityManager.flush();
    }

    @Override
    public void deleteCarEngine(int productId) {
        entityManager.remove(getCarEngine(productId));
    }
}
