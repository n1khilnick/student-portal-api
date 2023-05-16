package com.geekster.studentportal.services;

import com.geekster.studentportal.models.Address;
import com.geekster.studentportal.repositories.IAddressDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    IAddressDao addressDao;


    public void saveAddress(Address address){
        addressDao.save(address);
    }
    public List<Address> findAllAddress() {
        return addressDao.findAll();
    }
    @Transactional
    public void updateAddress(Long addressId, String landmark) {
        boolean isValidAddress = addressDao.existsById(Math.toIntExact(addressId));

        if(!isValidAddress){
            throw new IllegalStateException("Not valid address");
        }
        addressDao.UpdateAddress(addressId, landmark);
    }

    @Transactional
    public void deleteAddress(Integer addressId) {
        boolean isValidAddress = addressDao.existsById(addressId);

        if(!isValidAddress){
            throw new IllegalStateException("Not valid address");
        }
        addressDao.deleteById(addressId);
    }

}
