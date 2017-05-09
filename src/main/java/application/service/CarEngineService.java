package application.service;

import application.dao.CarEngineDAO;
import application.model.CarEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: militer
 * Date: 02.04.2017.
 */
@Service
public class CarEngineService {
    private final CarEngineDAO carEngineDAO;

    @Autowired
    public CarEngineService(CarEngineDAO carEngineDAO) {
        this.carEngineDAO = carEngineDAO;
    }

    public synchronized void createCarEngine(CarEngine carEngine) {
        carEngineDAO.addCarEngine(carEngine);
    }

    public CarEngine getCarEngine(int carEngineId) {
        return carEngineDAO.getCarEngine(carEngineId);
    }

    public List<CarEngine> getAllCarEngines() {
        return carEngineDAO.getAllCarEngines();
    }
}
