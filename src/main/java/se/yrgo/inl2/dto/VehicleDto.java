package se.yrgo.inl2.dto;

import se.yrgo.inl2.entity.Vehicle;

public record VehicleDto(
        Long id,
        String registrationNumber,
        String brand,
        String model,
        int productionYear,
        String customer
) {

    public static VehicleDto createDto(Vehicle v) {
        return new VehicleDto(
                v.getId(),
                v.getRegistrationNumber(),
                v.getBrand(),
                v.getModel(),
                v.getProductionYear(),
                v.getCustomer() != null ? v.getCustomer().getName() : null
        );
    }


}