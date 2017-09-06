package com.epam.demo.controllers;

import com.epam.demo.domain.Car;
import com.epam.demo.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Timur_Khudiakov on 9/5/2017.
 */
@RestController
public class CarsController {

    private CarsService carsService;

    @Autowired
    public void setCarsService(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/cars")
    public List<Car> getCarsByBrand(@RequestParam String brand) {
        return carsService.getCarsByBrand(brand);
    }

}
