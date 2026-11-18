package se.yrgo.inl2.dto;

import se.yrgo.inl2.entity.Vehicle;

public class VehicleBrandDto implements VehicleBrandProjection {
    private final String registrationNumber;
    private final String brand;

    public static VehicleBrandDto createDto(Vehicle vehicle) {
        return new VehicleBrandDto(vehicle.getRegistrationNumber(), vehicle.getBrand());
    }

    public VehicleBrandDto(String registrationNumber, String brand) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

}
