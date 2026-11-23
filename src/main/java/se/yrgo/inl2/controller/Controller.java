package se.yrgo.inl2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.inl2.dto.*;
import se.yrgo.inl2.entity.*;
import se.yrgo.inl2.repository.*;

import java.util.List;

// The endpoints are all over the place, so I put everything in the same controller
@RestController
public class Controller {

    CustomerRepository customerRepository;
    VehicleRepository vehicleRepository;

    public Controller(CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // A. Create a customer
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestParam String name, @RequestParam String phone) {
        var newCustomer = new Customer(name, phone);
        customerRepository.save(newCustomer);

        var newCustomerDto = CustomerDto.createDto(newCustomer);
        return ResponseEntity.ok(newCustomerDto);
    }

    // B. Create a vehicle
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(
            @RequestParam String registrationNumber,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam int productionYear
    ) {
        var newVehicle = new Vehicle(registrationNumber, brand, model, productionYear);
        vehicleRepository.save(newVehicle);
        var newVehicleDto = VehicleDto.createDto(newVehicle);
        return ResponseEntity.ok(newVehicleDto);
    }

    // C. Bind a vehicle to a customer
    @PostMapping("/addvehicletocustomer")
    public ResponseEntity<CustomerDto> addVehicleToCustomer(@RequestParam Long customerId, @RequestParam Long vehicleId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        vehicle.setCustomer(customer);
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(CustomerDto.createDto(customer));
    }

    // D. Return a list of customers and their vehicles
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        var customers = customerRepository.findAllCustomersAndTheirVehicles()
                .stream().map(CustomerDto::createDto).toList();
        return ResponseEntity.ok(customers);
    }


    // E. Return a list of all vehicles
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        var vehicles = vehicleRepository.findAll()
                .stream()
                .map(VehicleDto::createDto)
                .toList();

        return ResponseEntity.ok(vehicles);
    }

    // F. Return a list of all vehicles that belong to a certain brand
    @GetMapping("/vehicles-by-brand")
    public ResponseEntity<List<VehicleDto>> getAllVehiclesByBrand(@RequestParam String brand) {
        var vehicles = vehicleRepository.findAllByBrand(brand)
                .stream().map(VehicleDto::createDto)
                .toList();
        return ResponseEntity.ok(vehicles);
    }

    // G. Takes a customer name and returns ID
    // Since name is not unique, this is not a useful endpoint.
    @GetMapping("customer-id")
    public ResponseEntity<CustomerIdDto> getCustomer(@RequestParam String name) {
        Customer customer = customerRepository.findFirstByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // I think all REST responses should use JSON format, so I made a DTO just for ID.
        return ResponseEntity.ok(new CustomerIdDto(customer.getId()));
    }

    // H. Update the brand for a car
    @PutMapping("/vehicles/{vehicleId}/brand")
    public ResponseEntity<VehicleDto> updateVehicleBrand(@PathVariable Long vehicleId, @RequestParam String brand) {
        int updated = vehicleRepository.updateBrandNameById(vehicleId, brand);

        if (updated == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(VehicleDto.createDto(vehicle));
    }

    // I. List registration numbers and brand
    @GetMapping("/vehicle-brand-info")
    public ResponseEntity<List<VehicleBrandDto>> getAllVehicleBrandInfo() {
        List<VehicleBrandProjection> vbpList = vehicleRepository.findAllRegsAndBrands();

        List<VehicleBrandDto> vehicles = vbpList.stream().map(vbp ->
                    new VehicleBrandDto(vbp.getRegistrationNumber(), vbp.getBrand())
                ).toList();

        return ResponseEntity.ok(vehicles);
    }

}
