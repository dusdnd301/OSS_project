package com.buskspot.service;

import com.buskspot.domain.EquipmentRental;
import com.buskspot.repository.EquipmentRentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentRentalService {

    private final EquipmentRentalRepository rentalRepository;

    public EquipmentRentalService(EquipmentRentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public EquipmentRental rentEquipment(EquipmentRental rental) {
        return rentalRepository.save(rental);
    }

    public List<EquipmentRental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
