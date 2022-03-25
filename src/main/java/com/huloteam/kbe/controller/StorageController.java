package com.huloteam.kbe.controller;

import com.huloteam.kbe.model.ProductEntity;
import com.huloteam.kbe.orm.DatabaseCommunicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class StorageController {
    private final DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();

    // Get - Product information (3 attributes)
    @RequestMapping(value = "/storage")
    public ResponseEntity<Object> provideCalculatedPrice(@RequestParam(name="productName") String productName) {
        List<ProductEntity> productEntityList = databaseCommunicator.findProductEntity(productName);

        if (productEntityList != null) {
            if (productEntityList.size() == 1) {
                return new ResponseEntity<>(productEntityList.get(0).toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("To many results. Specify the product name!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Product information couldn't be found!", HttpStatus.BAD_REQUEST);
        }
    }
}
