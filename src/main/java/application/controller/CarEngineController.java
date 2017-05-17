package application.controller;

import application.model.CarEngine;
import application.service.CarEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: militer
 * Date: 26.03.2017.
 */
@RestController
@RequestMapping("/")
public class CarEngineController {
    private CarEngineService carEngineService;

    @Autowired
    public CarEngineController(CarEngineService carEngineService) {
        this.carEngineService = carEngineService;
    }

    @PostMapping
    public void create(@RequestBody CarEngine carEngine) {
        carEngineService.createCarEngine(carEngine);
    }

    @GetMapping("/{id}")
    public CarEngine get(@PathVariable("id") int id) {
        return carEngineService.getCarEngine(id);
    }

    @GetMapping
    public List<CarEngine> getAll() {
        return carEngineService.getAllCarEngines();
    }
}
