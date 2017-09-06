package com.epam.demo.service;

import com.epam.demo.consumer.wsdl.GetCarsByBrandInParams;
import com.epam.demo.consumer.wsdl.GetCarsByBrandOutResultSetRow;
import com.epam.demo.consumer.wsdl.GetCarsByBrandRequest;
import com.epam.demo.consumer.wsdl.GetCarsByBrandResponse;
import com.epam.demo.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Timur_Khudiakov on 9/5/2017.
 */
@Service
public class CarsService {

    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public CarsService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public List<Car> getCarsByBrand(String brand) {
        GetCarsByBrandRequest request = new GetCarsByBrandRequest();
        GetCarsByBrandInParams inParams = new GetCarsByBrandInParams();
        inParams.setBrandName(brand);
        request.setInParams(inParams);
        GetCarsByBrandResponse response =
                (GetCarsByBrandResponse) webServiceTemplate
                        .marshalSendAndReceive(request);
        List<Car> cars = response.getOutParams().getResultSet().getResultSetRow().stream().map(this::mapRow).collect(Collectors.toList());
        return cars;
    }

    private Car mapRow(GetCarsByBrandOutResultSetRow row) {
        Car car = new Car();
        row.setBrand(car.getBrand());
        row.setColor(car.getColor());
        row.setModel(car.getModel());
        return car;
    }

}
