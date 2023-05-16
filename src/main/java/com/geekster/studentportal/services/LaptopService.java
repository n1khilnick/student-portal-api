package com.geekster.studentportal.services;
import com.geekster.studentportal.models.Book;
import com.geekster.studentportal.models.Laptop;
import com.geekster.studentportal.repositories.ILaptopDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {
    @Autowired
    ILaptopDao laptopDao;

    public void saveLaptop(Laptop laptop) {
        laptopDao.save(laptop);
    }

    public Iterable<Laptop> getAllLaptops() {
        return laptopDao.findAll();
    }

    public Laptop fetchLaptopById(Long laptopId) {
        return laptopDao.findByLaptopId(laptopId);
    }

    @Transactional
    public void removeLaptopById(Long laptopId) {
        laptopDao.deleteLaptopByLaptopId(Math.toIntExact(laptopId));
    }

    @Transactional
    public void modifyLaptopPriceById(Long laptopId, Double price) {
        laptopDao.updateLaptopPriceByLaptopId(laptopId,price);
    }
}
