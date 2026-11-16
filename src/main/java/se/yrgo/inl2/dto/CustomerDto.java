package se.yrgo.inl2.dto;

import se.yrgo.inl2.entity.Customer;
import se.yrgo.inl2.entity.Vehicle;

import java.util.List;

public record CustomerDto(
        Long id,
        String name,
        String phoneNumber,
        List<String> vehicles) {

    public static CustomerDto createDto(Customer c) {
        return new CustomerDto(
                c.getId(),
                c.getName(),
                c.getPhoneNumber(),
                c.getVehicles().stream().map(Vehicle::getBrand).toList()
        );
    }

}
