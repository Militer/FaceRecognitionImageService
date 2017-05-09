package application.dao;

import application.model.CarEngine;

import java.util.List;

/**
 * User: militer
 * Date: 23.04.2017.
 */
public interface CarEngineDAO {
    void addCarEngine(CarEngine carEngine);

    CarEngine getCarEngine(int carEngineId);

    List<CarEngine> getAllCarEngines();

    void updateCarEngine(CarEngine newCarEngine);

    void deleteCarEngine(int productId);
}
