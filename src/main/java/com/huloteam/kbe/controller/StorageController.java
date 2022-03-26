package com.huloteam.kbe.controller;

import com.huloteam.kbe.validator.Validator;
import com.huloteam.kbe.orm.DatabaseCommunicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StorageController {
    private final DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();

    // Get - Product information (3 attributes)
    @RequestMapping(value = "/storage")
    public ResponseEntity<Object> provideProductData(@RequestParam(name="productName") String productName) {
        List<String> productEntityList = databaseCommunicator.findProductEntity(productName);

        if (Validator.isStringListNotNull(productEntityList)) {
            return new ResponseEntity<>(productEntityList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product data couldn't be found!", HttpStatus.BAD_REQUEST);
        }
    }
}
