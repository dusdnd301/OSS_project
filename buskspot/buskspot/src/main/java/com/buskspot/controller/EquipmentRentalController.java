package com.buskspot.controller;

import com.buskspot.domain.EquipmentRental;
import com.buskspot.service.EquipmentRentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class EquipmentRentalController {

    private final EquipmentRentalService rentalService;

    public EquipmentRentalController(EquipmentRentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rent(@RequestBody EquipmentRental rental) {
        return ResponseEntity.ok(rentalService.rentEquipment(rental));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipmentRental>> allRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
}
