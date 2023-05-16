package com.geekster.studentportal.controllers;

import com.geekster.studentportal.models.Laptop;
import com.geekster.studentportal.services.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/laptops")
public class LaptopController {
    @Autowired
    LaptopService laptopService;

    @PostMapping(value = "/")
    public void addLaptop(@RequestBody Laptop laptop){
        laptopService.saveLaptop(laptop);
    }

    @GetMapping(value = "/")
    public Iterable<Laptop> getAllLaptops(){
        return laptopService.getAllLaptops();
    }

    @GetMapping(value = "/{laptopId}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable Long laptopId) {
        HttpStatus status = null;
        Laptop laptop = laptopService.fetchLaptopById(laptopId);
        if (laptop == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Laptop>(laptop, status);

    }

    @PutMapping(value = "/update/id/{laptopId}/price/{price}")
    public ResponseEntity<String> updatelaptopPriceById(@PathVariable Long laptopId,@PathVariable Double price){

        Laptop laptop = laptopService.fetchLaptopById(laptopId);
        String message = null;
        HttpStatus status;

        if(laptop == null){
            status = HttpStatus.NOT_MODIFIED;
            message = "id "+laptopId+" is not valid";
        }
        else{
            status = HttpStatus.OK;
            laptopService.modifyLaptopPriceById(laptopId,price);

        }
        return new ResponseEntity<String>(message,status);
    }

    @DeleteMapping("/id/{laptopId}")
    public ResponseEntity<String> deleteLaptopById(@PathVariable Long laptopId){
        String message = null;
        HttpStatus status;

        try{
            laptopService.removeLaptopById(laptopId);
            status = HttpStatus.OK;
        }catch (Exception ex){
            status = HttpStatus.BAD_REQUEST;

        }
        return new ResponseEntity<String>(message,status);
    }
}
