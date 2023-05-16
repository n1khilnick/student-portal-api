package com.geekster.studentportal.controllers;

import com.geekster.studentportal.models.Address;
import com.geekster.studentportal.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping(value = "/")
    public void addAddress(@RequestBody Address address){

        addressService.saveAddress(address);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddress(){
        List<Address>addresses =addressService.findAllAddress();
        HttpStatus status;

        if(addresses.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity<List<Address>>(addresses,status);
    }

    @PutMapping("addressId/{addressId}/landmark/{landmark}")
    public ResponseEntity<String>modifyAddress(@PathVariable Long addressId,@PathVariable String landmark){
        HttpStatus status;
        String message ;
        try{
            addressService.updateAddress(addressId, landmark);
            status = HttpStatus.OK;
            message = "Address Landmark  updated successfully !!!";

        }
        catch (Exception ex){

            status = HttpStatus.NOT_MODIFIED;
            message = "Address with addressId "+addressId+" not exist";
            System.out.println(ex);


        }
        return new ResponseEntity<String>(message,status);
    }

    @DeleteMapping("addressId/{addressId}")
    public ResponseEntity<String>removeAddress(@PathVariable Long addressId){
        HttpStatus status;
        String message = null;
        try{
            addressService.deleteAddress(Math.toIntExact(addressId));
            status = HttpStatus.OK;
            message = "Address deleted successfully !!!";

        }
        catch (Exception ex){
            status = HttpStatus.BAD_REQUEST;
            message = "AddressId not exist";
            System.out.println(ex);
        }
        return new ResponseEntity<String>(message,status);
    }

}
